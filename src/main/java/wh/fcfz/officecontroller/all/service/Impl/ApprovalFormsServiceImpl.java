package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.*;
import wh.fcfz.officecontroller.all.bean.Dao.review.*;
import wh.fcfz.officecontroller.all.bean.Dto.AddApprovalFormsDto;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalFormsDto;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalFormsVo;
import wh.fcfz.officecontroller.all.mapper.*;
import wh.fcfz.officecontroller.all.service.ApprovalFormsService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.HashMap;
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

    //管理员查询审批数据
    @Override
    public Result getApprovalForms(MyPage<ApprovalFormsDto> myPage) {
        List<ApprovalFormsVo> approvalFormsList = approvalFormsMapper.getList(myPage.getData());
        approvalFormsList = approvalFormsList.stream().parallel().peek(approvalForm -> {
            List<String> fileUrlList = approvalFormsMapper.getFileList(approvalForm.getFormId()).stream().parallel().map(File::getFileUrl).toList();
            approvalForm.setFileList(fileUrlList);
        }).collect(Collectors.toList());
        approvalFormsList = GetList(approvalFormsList);
        List<ApprovalFormsVo> collect = approvalFormsList.stream()
                .skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize())
                .collect(Collectors.toList());
        Page<ApprovalFormsVo> page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        page.setRecords(collect);
        page.setTotal(approvalFormsList.size());
        return new Result("200", "查询成功", page);
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
    @ApiOperation(value = "修改审批status")
    public Result updateApprovalForms(MyPage<ApprovalSteps> myPage) {
        if (myPage.getData() == null) {
            return new Result(ResponseEnum.DATA_NOT_EXIST, null);
        }
        try {
            //修改steps表
            ApprovalSteps data = myPage.getData();
            data.setApprovalDate(new java.sql.Timestamp(System.currentTimeMillis()));
            approvalStepsServiceImpl.addApprovalSteps(data);
            //连着forms表一起修改
            ApprovalForms approvalForms = approvalFormsMapper.selectById(myPage.getData().getFormId());
            if (data.getResult().equals("同意")) {
                approvalForms.setStatus("已完成");
                if (approvalForms.getType().equals("请假")) {
                    User user = userMapper.selectById(approvalForms.getAllId());
                    user.setStatus(0);
                    try {
                        userMapper.updateById(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (approvalForms.getType().equals("出差")) {
                    User user = userMapper.selectById(approvalForms.getAllId());
                    user.setStatus(2);
                    try {
                        userMapper.updateById(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (approvalForms.getType().equals("补签")) {
                    Attendance attendance = attendanceMapper.selectById(approvalForms.getAllId());
                    attendance.setStatus("打卡成功");
                    try {
                        attendanceMapper.updateById(attendance);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                approvalForms.setStatus("未完成");
            }
            //修改approvalForms表单状态
            try {
                approvalFormsMapper.updateById(approvalForms);
                return new Result(ResponseEnum.SUCCESS, data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteApprovalForms(Long id) {
        ApprovalForms approvalForms = approvalFormsMapper.selectById(id);
        boolean b = deleteIdsAll(approvalForms.getAllId(), approvalForms.getType());
        if (b) {
            int i = approvalFormsMapper.deleteById(id);
            if(i>0){
                return true;
            }else {
                throw new RuntimeException("删除表单数据失败");
            }
        }else {
            throw new RuntimeException("删除表单详细数据失败");
        }
    }

    public boolean deleteIdsAll(Long id, String type) {
        switch (type) {
            case "请假":
                return leaveRequestsService.removeById(id);
            case "报销":
                return reimbursementMapper.deleteById(id) > 0;
            case "出差":
                return businessMapper.deleteById(id) > 0;
            case "加班":
                return overtimesMapper.deleteById(id) > 0;
            case "补签":
                return attandenceCorrectionsMapper.deleteById(id) > 0;
            case "入职":
                return onboardingsMapper.deleteById(id) > 0;
            case "培训":
                return trainingsMapper.deleteById(id) > 0;
            case "薪资调整":
                return salaryAdjustmentsMapper.deleteById(id) > 0;
            case "离职":
                return resignationsMapper.deleteById(id) > 0;
            case "采购":
                return procurementsMapper.deleteById(id) > 0;
            case "用车":
                return vehicleUsagesMapper.deleteById(id) > 0;
            case "预算":
                return budgetsMapper.deleteById(id) > 0;
            case "招聘":
                return recruitmentsMapper.deleteById(id) > 0;
            case "设备维修":
                return equipmentMaintenancesMapper.deleteById(id) > 0;
            case "合同签署":
                return contractSigningsMapper.deleteById(id) > 0;
            case "项目立项":
                return projectInitiationsMapper.deleteById(id) > 0;
        }
        return false;
    }

    public List<ApprovalFormsVo> GetList(List<ApprovalFormsVo> approvalFormsList) {
        return approvalFormsList = approvalFormsList.stream().parallel().map(approvalForm -> {
            Map<String, Object> map = new HashMap<>();
            switch (approvalForm.getType()) {
                case "请假": {
                    map.put("leave", leaveRequestsService.getById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                }
                case "报销":
                    map.put("reimbursement", reimbursementMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "出差":
                    map.put("business", businessMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "加班":
                    map.put("overtime", overtimesMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "补签":
                    map.put("attendanceCorrection", attandenceCorrectionsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "入职":
                    map.put("onboarding", onboardingsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "培训":
                    map.put("training", trainingsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "薪资调整":
                    map.put("salaryAdjustments", salaryAdjustmentsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "离职":
                    map.put("resignation", resignationsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "采购":
                    map.put("procurements", procurementsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "用车":
                    map.put("vehicleUsages", vehicleUsagesMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "预算":
                    map.put("budgets", budgetsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "招聘":
                    map.put("recruitments", recruitmentsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "设备维修":
                    map.put("equipmentMaintenances", equipmentMaintenancesMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "合同签署":
                    map.put("contractSignings", contractSigningsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
                case "项目立项":
                    map.put("projectInitiations", projectInitiationsMapper.selectById(approvalForm.getAllId()));
                    approvalForm.setMap(map);
                    return approvalForm;
            }
            return approvalForm;
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
}
