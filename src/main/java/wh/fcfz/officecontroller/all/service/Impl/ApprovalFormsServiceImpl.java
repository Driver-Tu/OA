package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.ApprovalForms;
import wh.fcfz.officecontroller.all.bean.User;
import wh.fcfz.officecontroller.all.mapper.ApprovalFormsMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.ApprovalFormsService;
import wh.fcfz.officecontroller.all.tool.MyPage;
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
        if (myPage.getParams().containsKey("departmentName") && (null != myPage.getParams().get("departmentName")) && myPage.getParams().get("departmentName") != "") {
            approvalFormsList = approvalFormsList.stream()
                    .filter(approvalForm -> myPage.getParams().get("departmentName").toString().equals(approvalForm.getDepartmentName()))
                    .collect(Collectors.toList());
        }
        List<ApprovalForms> collect = approvalFormsList.stream()
                .skip((long) (myPage.getPageNum() - 1) * myPage.getPageSize()).limit(myPage.getPageSize())
                .collect(Collectors.toList());
        page.setRecords(collect);
        page.setTotal(approvalFormsList.size());
        return new Result("200", "查询成功", page);
    }

    //添加审批数据
    @Override
    public Result addApprovalForms(ApprovalForms approvalForms) {
        if (approvalFormsMapper.insert(approvalForms) > 0) {
            return new Result("200", "添加成功", null);
        }
        return null;
    }
}
