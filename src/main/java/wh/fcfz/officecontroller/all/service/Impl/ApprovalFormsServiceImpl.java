package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalForms;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.bean.Dao.StepCount;
import wh.fcfz.officecontroller.all.bean.Dao.review.*;
import wh.fcfz.officecontroller.all.bean.Dto.AddApprovalFormsDto;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalFormsDto;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalFormsVo;
import wh.fcfz.officecontroller.all.mapper.*;
import wh.fcfz.officecontroller.all.service.ApprovalFormsService;
import wh.fcfz.officecontroller.all.tool.MyException;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ApprovalFormsServiceImpl extends ServiceImpl<ApprovalFormsMapper, ApprovalForms> implements ApprovalFormsService {

    @Autowired
    private ApprovalFormsMapper approvalFormsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LeaveRequestsServiceImpl leaveRequestsService;//请假
    @Autowired
    private BusinessTripsMapper businessMapper;//出差
    @Autowired
    private OvertimesMapper overtimesMapper;//加班
    @Autowired
    private AttendanceCorrectionsMapper attandenceCorrectionsMapper;//补签
    @Autowired
    private ReimbursementsMapper reimbursementMapper;//报销
    @Autowired
    private BudgetsMapper budgetsMapper;//预算
    @Autowired
    private OnboardingsMapper onboardingsMapper;//入职
    @Autowired
    private EquipmentMaintenancesMapper equipmentMaintenancesMapper;//维保
    @Autowired
    private ProcurementsMapper procurementsMapper;//采购
    @Autowired
    private TrainingsMapper trainingsMapper;//培训
    @Autowired
    private SalaryAdjustmentsMapper salaryAdjustmentsMapper;//薪资调整
    @Autowired
    private VehicleUsagesMapper vehicleUsagesMapper;//用车
    @Autowired
    private ResignationsMapper resignationsMapper;//离职
    @Autowired
    private RecruitmentsMapper recruitmentsMapper;//招聘
    @Autowired
    private ContractSigningsMapper contractSigningsMapper;//合同
    @Autowired
    private ProjectInitiationsMapper projectInitiationsMapper;//项目立项
    @Autowired
    private StepCountMapper stepCountMapper;
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //管理员查询审批数据
    @Override
    public Result getApprovalForms(MyPage<ApprovalFormsDto> myPage) {
        List<ApprovalFormsVo> approvalFormsList = getFormsVos(myPage);
        List<ApprovalFormsVo> collect = approvalFormsList.stream()
                .skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize())
                .collect(Collectors.toList());
        Page<ApprovalFormsVo> page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        page.setRecords(collect);
        page.setTotal(approvalFormsList.size());
        return new Result("200", "查询成功", page);
    }

    private List<ApprovalFormsVo> getFormsVos(MyPage<ApprovalFormsDto> myPage) {
        List<ApprovalFormsVo> approvalFormsList = getApprovalFormsVos(myPage);
        approvalFormsList = GetList(approvalFormsList);
        return approvalFormsList;
    }

    private List<ApprovalFormsVo> getApprovalFormsVos(MyPage<ApprovalFormsDto> myPage) {
        List<ApprovalFormsVo> approvalFormsList = approvalFormsMapper.getList(myPage.getData());
        approvalFormsList = approvalFormsList.stream().parallel().peek(approvalForm -> {
            List<String> fileUrlList = approvalFormsMapper.getFileList(Long.valueOf(approvalForm.getFormId())).stream().parallel().map(File::getFileUrl).toList();
            approvalForm.setFileList(fileUrlList);
        }).collect(Collectors.toList());
        return approvalFormsList;
    }


    //添加审批数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApprovalForms addApprovalForms(ApprovalForms approvalForms) {
        approvalForms.setApplicantId(StpUtil.getLoginIdAsInt());
        approvalForms.setApplicationDate(new java.sql.Timestamp(System.currentTimeMillis()));
        approvalForms.setStatus("已提交");
        try {
            //保存审批信息
            boolean save = this.save(approvalForms);
            return save ? approvalForms : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 确定特殊值
     * 情况一，当修改的审批表字段的类型为请假，员工状态变为0不在线，在结束假期后变成1在线
     * 情况二：当修改的审批表字段的类型为出差，员工的状态变为2出差中，在结束出差后变成1在线
     * 情况三：当审批类型为补签的时候，添加审批步骤，修改补签表状态为打卡成功，不修改员工状态
     */
    @Autowired
    private ApprovalStepsServiceImpl approvalStepsServiceImpl;
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateApprovalForms(String result, ApprovalForms approvalForms) {
        int approvalStepsCount = approvalFormsMapper.getApprovalStepsCount(Long.valueOf(approvalForms.getFormId()));
        StepCount stepCount = stepCountMapper.selectOne(new LambdaQueryWrapper<StepCount>()
                .eq(StepCount::getStepType, approvalForms.getType()));
        log.error("approvalStepsCount:"+approvalStepsCount+"========"+stepCount);
        //如果审批次数大于可以审批次数
        if(stepCount.getStepCount()<approvalStepsCount){
            throw new MyException("审批已完成，无法审批","10707");
        }
        //如果审批记录条数刚好等于可已审批次数
            if(result.equals("失败")){
                approvalForms.setStatus("未完成");
                int i = approvalFormsMapper.updateById(approvalForms);
                if(i>0){
                    return i;
                }else {
                    throw new MyException("修改失败","10707");
                }
            }else{
                if(stepCount.getStepCount()==approvalStepsCount){
                approvalForms.setStatus("已完成");
                int i = approvalFormsMapper.updateById(approvalForms);
                if(i>0){
                    return i;
                }else {
                    throw new MyException("修改失败","10707");
                }
            }else{
            //如果不等于审批次数，则修改为审批中
            approvalForms.setStatus("审批中");
            int i = approvalFormsMapper.updateById(approvalForms);
            if (i > 0) {
                return i;
            } else {
                throw new MyException("修改失败", "10707");
            }
        }
    }
    }

    @Override
    public boolean deleteApprovalForms(Long id) {
        ApprovalForms approvalForms = approvalFormsMapper.selectById(id);
        boolean b = deleteIdsAll(Long.valueOf(approvalForms.getAllId()), approvalForms.getType());
        if (b) {
            int i = approvalFormsMapper.deleteById(id);
            if(i>0){
                return true;
            }else {
                throw new MyException("删除失败","10703");
            }
        }else {
            throw new MyException("删除失败","10703");
        }
    }

    public boolean deleteIdsAll(Long id, String type) {
        return switch (type) {
            case "请假" -> leaveRequestsService.removeById(id);
            case "报销" -> reimbursementMapper.deleteById(id) > 0;
            case "出差" -> businessMapper.deleteById(id) > 0;
            case "加班" -> overtimesMapper.deleteById(id) > 0;
            case "补签" -> attandenceCorrectionsMapper.deleteById(id) > 0;
            case "入职" -> onboardingsMapper.deleteById(id) > 0;
            case "培训" -> trainingsMapper.deleteById(id) > 0;
            case "薪资调整" -> salaryAdjustmentsMapper.deleteById(id) > 0;
            case "离职" -> resignationsMapper.deleteById(id) > 0;
            case "采购" -> procurementsMapper.deleteById(id) > 0;
            case "用车" -> vehicleUsagesMapper.deleteById(id) > 0;
            case "预算" -> budgetsMapper.deleteById(id) > 0;
            case "招聘" -> recruitmentsMapper.deleteById(id) > 0;
            case "设备维修" -> equipmentMaintenancesMapper.deleteById(id) > 0;
            case "合同签署" -> contractSigningsMapper.deleteById(id) > 0;
            case "项目立项" -> projectInitiationsMapper.deleteById(id) > 0;
            default -> false;
        };
    }

    public List<ApprovalFormsVo> GetList(List<ApprovalFormsVo> approvalFormsList) {
        return approvalFormsList.stream().parallel().peek(approvalForm -> {
            Integer allId = approvalForm.getAllId();
            StringBuilder stringBuilder = new StringBuilder();
            formMapper.getFormField(allId).forEach(formFieldAllDomain -> {
//                String.valueOf(formFieldAllDomain.getFieldId())
//                        , formFieldAllDomain.getFieldName()+","+formFieldAllDomain.getFieldValue();
               //判断是app的token还是ps端的token
                if(StpUtil.getSessionByLoginId(StpUtil.getLoginIdAsLong()).getTokenValueListByDevice("app").get(0).equals(StpUtil.getTokenValue())){
                    stringBuilder.append("<view><text>").append(formFieldAllDomain.getFieldName()).append(":</text><text>").append(formFieldAllDomain.getFieldValue()).append("</text></view>");
                }else {
                    stringBuilder.append("<span><strong>").append(formFieldAllDomain.getFieldName()).append(":<strong>").append(formFieldAllDomain.getFieldValue()).append("</span><br>");
                }
            });
            approvalForm.setDescription(stringBuilder.toString());
        }).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public Long setDetail(AddApprovalFormsDto addApprovalFormsDto) {
        Object object = addApprovalFormsDto.getObject();
        try {
            switch (addApprovalFormsDto.getApprovalForms().getType()) {
                case "请假": {
                    LeaveRequests leaveRequests = new LeaveRequests();
                    BeanUtil.copyProperties(object, leaveRequests);
                    leaveRequests.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = leaveRequestsService.save(leaveRequests);
                    return leaveRequests.getLeaveRequestId();
                }
                case "报销": {
                    Reimbursements reimbursement = new Reimbursements();
                    BeanUtil.copyProperties(object, reimbursement);
                    reimbursement.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = reimbursementMapper.insert(reimbursement) > 0;
                    return reimbursement.getReimbursementId();
                }
                case "出差": {
                    BusinessTrips businessTrips = new BusinessTrips();
                    BeanUtil.copyProperties(object, businessTrips);
                    businessTrips.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = businessMapper.insert(businessTrips) > 0;
                    return businessTrips.getBusinessTripId();
                }
                case "加班": {
                    Overtimes overtimes = new Overtimes();
                    BeanUtil.copyProperties(object, overtimes);
                    overtimes.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = overtimesMapper.insert(overtimes) > 0;
                    return overtimes.getOvertimeId();
                }
                case "补签": {
                    AttendanceCorrections attendanceCorrections = new AttendanceCorrections();
                    BeanUtil.copyProperties(object, attendanceCorrections);
                    attendanceCorrections.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = attandenceCorrectionsMapper.insert(attendanceCorrections) > 0;
                    return attendanceCorrections.getCorrectionId();
                }
                case "入职": {
                    Onboardings onboardings = new Onboardings();
                    BeanUtil.copyProperties(object, onboardings);
                    onboardings.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = onboardingsMapper.insert(onboardings) > 0;
                    return onboardings.getOnboardingId();
                }
                case "培训": {
                    Trainings trainings = new Trainings();
                    BeanUtil.copyProperties(object, trainings);
                    trainings.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = trainingsMapper.insert(trainings) > 0;
                    return trainings.getTrainingId();
                }
                case "薪资调整": {
                    SalaryAdjustments salaryAdjustments = new SalaryAdjustments();
                    BeanUtil.copyProperties(object, salaryAdjustments);
                    salaryAdjustments.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = salaryAdjustmentsMapper.insert(salaryAdjustments) > 0;
                    return salaryAdjustments.getAdjustmentId();
                }
                case "离职": {
                    Resignations resignations = new Resignations();
                    BeanUtil.copyProperties(object, resignations);
                    resignations.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = resignationsMapper.insert(resignations) > 0;
                    return resignations.getResignationId();
                }
                case "采购": {
                    Procurements procurements = new Procurements();
                    BeanUtil.copyProperties(object, procurements);
                    boolean save = procurementsMapper.insert(procurements) > 0;
                    return procurements.getProcurementId();
                }
                case "用车": {
                    VehicleUsages vehicleUsages = new VehicleUsages();
                    BeanUtil.copyProperties(object, vehicleUsages);
                    vehicleUsages.setEmployeeId(StpUtil.getLoginIdAsLong());
                    boolean save = vehicleUsagesMapper.insert(vehicleUsages) > 0;
                    return vehicleUsages.getVehicleUsageId();
                }
                case "预算": {
                    Budgets budgets = new Budgets();
                    BeanUtil.copyProperties(object, budgets);
                    boolean save = budgetsMapper.insert(budgets) > 0;
                    return budgets.getBudgetId();
                }
                case "招聘": {
                    Recruitments recruitments = new Recruitments();
                    BeanUtil.copyProperties(object, recruitments);
                    boolean save = recruitmentsMapper.insert(recruitments) > 0;
                    return recruitments.getRecruitmentId();
                }
                case "设备维修": {
                    EquipmentMaintenances equipmentMaintenances = new EquipmentMaintenances();
                    BeanUtil.copyProperties(object, equipmentMaintenances);
                    boolean save = equipmentMaintenancesMapper.insert(equipmentMaintenances) > 0;
                    return equipmentMaintenances.getMaintenanceId();
                }
                case "合同签署": {
                    ContractSignings contractSignings = new ContractSignings();
                    BeanUtil.copyProperties(object, contractSignings);
                    boolean save = contractSigningsMapper.insert(contractSignings) > 0;
                    return contractSignings.getContractSigningId();
                }
                case "项目立项": {
                    ProjectInitiations projectInitiations = new ProjectInitiations();
                    BeanUtil.copyProperties(object, projectInitiations);
                    boolean save = projectInitiationsMapper.insert(projectInitiations) > 0;
                    return projectInitiations.getProjectInitiationId();
                }
            }
            return 0L;
        } catch (Exception e) {
            log.error("保存申请信息时出现异常，object: {}", object, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询审批数据
     */

    public String setExcelApprovalForms(MyPage<ApprovalFormsDto> myPage) {
        List<ApprovalFormsVo> approvalFormsVos = getFormsVos(myPage);
        List<String> approvalDatailList=new ArrayList<>();
        //获取所有的map，并且存入集合
        //根据不同的审批获取对应的字段名
        String className="";
        Map<String, Object> map = approvalFormsVos.get(0).getMap();
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            className=stringObjectEntry.getKey();
        }
        //创建一个
        Field[] declaredFields1 = ApprovalFormsVo.class.getDeclaredFields();
        for (Field field : declaredFields1) {
            approvalDatailList.add(field.getName());
        }
        approvalDatailList.remove(10);
        //获取表头
        extracted(className, approvalDatailList);
        List<List<String>> head=new ArrayList<>();
        head.add(approvalDatailList);
        List<List<Object>> data=new ArrayList<>();
        // 3. 创建写入对象
        String fileName = "output.xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(fileName).build()) {

            // 4. 创建写入工作表
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").head(head).build();

            // 5. 写入数据
            excelWriter.write(data, writeSheet);

            // 6. 关闭写入对象
            excelWriter.finish();
        }
        return null;
    }

    private static void extracted(String className, List<String> approvalDatailList) {
        switch (className){
            case "leave":{
                Class<?> aClass = LeaveRequests.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "business":{
                Class<?> aClass = BusinessTrips.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "overtimes":{
                Class<?> aClass = Overtimes.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "attendanceCorrections":{
                Class<?> aClass = AttendanceCorrections.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "reimbursement":{
                Class<?> aClass = Reimbursements.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "budget":{
                Class<?> aClass = Budgets.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "onboarding":{
                Class<?> aClass = Onboardings.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "equipmentMaintenance":{
                Class<?> aClass = EquipmentMaintenances.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "procurement":{
                Class<?> aClass = Procurements.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "training":{
                Class<?> aClass = Trainings.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "salaryAdjustment":{
                Class<?> aClass = SalaryAdjustments.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "vehicleUsage":{
                Class<?> aClass = VehicleUsages.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "resignation":{
                Class<?> aClass = Resignations.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "recruitment":{
                Class<?> aClass = Recruitments.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "contractSigning":{
                Class<?> aClass = ContractSignings.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            case "projectInitiation":{
                Class<?> aClass = ProjectInitiations.class;
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields){
                    approvalDatailList.add(declaredField.getName());
                }
                break;
            }
            default:{
                throw new MyException("400","没有该类型");
            }
        }
    }
}
