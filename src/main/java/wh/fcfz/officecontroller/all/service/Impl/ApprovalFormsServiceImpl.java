package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.ApprovalForms;
import wh.fcfz.officecontroller.all.bean.ApprovalSteps;
import wh.fcfz.officecontroller.all.bean.Attendance;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.mapper.ApprovalFormsMapper;
import wh.fcfz.officecontroller.all.mapper.AttendanceMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.ApprovalFormsService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ApprovalFormsServiceImpl extends ServiceImpl<ApprovalFormsMapper, ApprovalForms> implements ApprovalFormsService {

    @Autowired
    private ApprovalFormsMapper approvalFormsMapper;
    @Autowired
    private UserMapper userMapper;

    //管理员查询审批数据
    @Override
    public Result getApprovalForms(MyPage<ApprovalForms> myPage) {
        Page<ApprovalForms> page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        LambdaQueryWrapper<ApprovalForms> queryWrapper = new LambdaQueryWrapper<>();
        //查询审批状态
        queryWrapper.eq(null != myPage.getData().getStatus() && (!myPage.getData().getStatus().equals("")), ApprovalForms::getStatus, myPage.getData().getStatus())
                //查询审批类型
                .eq(null != myPage.getData().getType() && (!myPage.getData().getType().equals("")), ApprovalForms::getType, myPage.getData().getType())
                .orderByDesc(ApprovalForms::getApplicationDate);
        List<ApprovalForms> approvalForms = approvalFormsMapper.selectList(queryWrapper);
        List<ApprovalForms> approvalFormsList = approvalForms.stream().peek(approvalForm -> {
            User user = userMapper.selectById(approvalForm.getApplicantId());
            String departName = userMapper.selectDepartName(user.getDepartmentId());
            approvalForm.setUserName(user.getUserName());
            approvalForm.setDepartmentName(departName);
        }).collect(Collectors.toList());
        //如果map中含有userName的话，就删除不满足该名字的数据
        if (myPage.getParams().containsKey("userName") && (null != myPage.getParams().get("userName")) && myPage.getParams().get("userName") != "") {
            String userName = myPage.getParams().get("userName").toString();
            approvalFormsList = approvalFormsList.stream()
                    .filter(approvalForm -> approvalForm.getUserName().equals(userName))
                    .collect(Collectors.toList());
        }
        //如果map中含有departmentName的话，就删除不满足该名字的数据
        User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
        String departName = userMapper.selectDepartName(user.getDepartmentId());
        approvalFormsList = approvalFormsList.stream()
                    .filter(approvalForm -> departName.equals(approvalForm.getDepartmentName()))
                    .collect(Collectors.toList());
        List<ApprovalForms> collect = approvalFormsList.stream()
                .skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize())
                .collect(Collectors.toList());
        page.setRecords(collect);
        page.setTotal(approvalFormsList.size());
        return new Result("200", "查询成功", page);
    }

    //添加审批数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addApprovalForms(ApprovalForms approvalForms) {
        if(approvalForms==null){
            return new Result(ResponseEnum.DATA_NOT_EXIST,null);
        }

        return null;
    }

    /**
     * 确定特殊值
     * 情况一，当修改的审批表字段的类型为请假，员工状态变为0不在线，在结束假期后变成1在线
     * 情况二：当修改的审批表字段的类型为出差，员工的状态变为2出差中，在结束出差后变成1在线
     * 情况三：当审批类型为补签的时候，添加审批步骤，修改补签表状态为打卡成功，不修改员工状态
     * */
    @Autowired
    private ApprovalStepsServiceImpl approvalStepsServiceImpl;
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "修改审批status")
    public Result updateApprovalForms(MyPage<ApprovalSteps> myPage) {
        if (myPage.getData()==null){
            return new Result(ResponseEnum.DATA_NOT_EXIST,null);
        }
        try {
            //修改steps表
            ApprovalSteps data = myPage.getData();
            data.setApprovalDate(new java.sql.Timestamp(System.currentTimeMillis()));
            data.setApprover(userMapper.selectById(StpUtil.getLoginIdAsLong()).getUserName());
            approvalStepsServiceImpl.addApprovalSteps(data);
            //连着forms表一起修改
            ApprovalForms approvalForms = approvalFormsMapper.selectById(myPage.getData().getFormId());
            if(data.getResult().equals("同意")){
                approvalForms.setStatus("已完成");
                if(approvalForms.getType().equals("请假")){
                    User user = userMapper.selectById(approvalForms.getApplicantId());
                    user.setStatus(0);
                    try {
                        userMapper.updateById(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else if(approvalForms.getType().equals("出差")){
                    User user = userMapper.selectById(approvalForms.getApplicantId());
                    user.setStatus(2);
                    try {
                        userMapper.updateById(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else if(approvalForms.getType().equals("补签")){
                    Attendance attendance=attendanceMapper.selectById(approvalForms.getAttendanceId());
                    attendance.setStatus("打卡成功");
                    try {
                        attendanceMapper.updateById(attendance);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                approvalForms.setStatus("未完成");
            }
            //修改approvalForms表单状态
            try {
                approvalFormsMapper.updateById(approvalForms);
                return new Result(ResponseEnum.SUCCESS,data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
