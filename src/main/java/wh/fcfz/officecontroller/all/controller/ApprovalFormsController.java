package wh.fcfz.officecontroller.all.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalForms;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;
import wh.fcfz.officecontroller.all.bean.Dto.AddApprovalFormsDto;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalFormsDto;
import wh.fcfz.officecontroller.all.service.Impl.ApprovalFormsServiceImpl;
import wh.fcfz.officecontroller.all.service.Impl.ApprovalStepsServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/approvalForms")
public class ApprovalFormsController {

    @Autowired
    private  ApprovalFormsServiceImpl approvalFormsService;
    @Autowired
    private ApprovalStepsServiceImpl approvalStepsServiceImpl;

    @PostMapping("/getApprovalForms")
    public Result addApprovalForms(@RequestBody MyPage<ApprovalFormsDto> approvalForms)
    {
        return approvalFormsService.getApprovalForms(approvalForms);
    }

    @PostMapping("/getSelfApprovalForms")
    public Result getSelfApprovalForms(@RequestBody MyPage<ApprovalFormsDto> approvalForms)
    {
        approvalForms.getData().setApplicantId(StpUtil.getLoginIdAsInt());
        return approvalFormsService.getApprovalForms(approvalForms);
    }

    @PostMapping("/updateApprovalForms")
    @SaCheckPermission("admin")
    public Result updateApprovalFroms(@RequestBody MyPage<ApprovalSteps> approvalSteps)
    {
        return approvalFormsService.updateApprovalForms(approvalSteps);
    }

    @PostMapping("/addApprovalForms")
    @Transactional
    public Result addApprovalForms(AddApprovalFormsDto addApprovalFormsDto)
    {
       if(addApprovalFormsDto.getApprovalForms()==null){
           return new Result(ResponseEnum.DATA_NOT_EXIST,null);
       }
        ApprovalForms AddApprovalForms = approvalFormsService.addApprovalForms(addApprovalFormsDto.getApprovalForms());
        List<Integer> approvers = addApprovalFormsDto.getApprovers();
        Stream<Integer> appSteps = approvers.stream().parallel().peek(approver -> {
            ApprovalSteps approvalSteps = new ApprovalSteps();
            approvalSteps.setFormId(AddApprovalForms.getFormId());
            approvalSteps.setApprover(approver);
            ApprovalSteps AddApprovalStep = approvalStepsServiceImpl.addApprovalSteps(approvalSteps);
        });
        return new Result(ResponseEnum.SUCCESS,true);
    }
}
