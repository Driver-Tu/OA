package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.*;
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
    private LeaveMapper leaveMapper;
    @Autowired
    private BusinessMapper businessMapper;
    //管理员查询审批数据
    @Override
    public Result getApprovalForms(MyPage<ApprovalFormsDto> myPage) {
        List<ApprovalFormsVo> approvalFormsList = approvalFormsMapper.getList(myPage.getData());
        approvalFormsList = approvalFormsList.stream().parallel().peek(approvalForm -> {
            List<String> fileUrlList = approvalFormsMapper.getFileList(approvalForm.getFormId()).stream().parallel().map(File::getFileUrl).toList();
            approvalForm.setFileUrlList(fileUrlList);
        }).collect(Collectors.toList());
        approvalFormsList =GetList(approvalFormsList);
        List<ApprovalFormsVo> collect = approvalFormsList.stream()
                .skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize())
                .collect(Collectors.toList());
        Page<ApprovalFormsVo> page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        page.setRecords(collect);
        page.setTotal(approvalFormsList.size());
        return new Result("200", "查询成功", page);
    }
    public List<ApprovalFormsVo> GetList(List<ApprovalFormsVo> approvalFormsList){
        return approvalFormsList = approvalFormsList.stream().parallel().map(approvalForm -> {
            switch (approvalForm.getType()) {
                case "请假": {
                    LeaveFrom leaveFrom = leaveMapper.selectById(approvalForm.getAllId());
                    Map<String,Object> map = new HashMap<>();
                    map.put("leave", leaveFrom);
                    approvalForm.setMap(map);
                    return approvalForm;
                }
                case "报销":
                    return approvalForm;
                case "出差":
                    Business business = businessMapper.selectById(approvalForm.getAllId());
                    Map<String,Object> map = new HashMap<>();
                    map.put("business", business);
                    approvalForm.setMap(map);
                    return approvalForm;
                case "加班":
                    return approvalForm;
                case "补签":
                    return approvalForm;
                case "入职":
                    return approvalForm;
                case "培训":
                    return approvalForm;
                case "薪资调整":
                    return approvalForm;
                case "离职":
                    return approvalForm;
                case "采购":
                    return approvalForm;
                case "用车":
                    return approvalForm;
                case "预算":
                    return approvalForm;
                case "招聘":
                    return approvalForm;
                case "设备维修":
                    return approvalForm;
                case "合同签署":
                    return approvalForm;
                case "项目立项":
                    return approvalForm;
            }
            return approvalForm;
        }).collect(Collectors.toList());
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
        }catch (Exception e){
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
            data.setApprover(userMapper.selectById(StpUtil.getLoginIdAsLong()).getUserName());
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
}
