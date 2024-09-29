package wh.fcfz.officecontroller.all.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.ApprovalForms;
import wh.fcfz.officecontroller.all.bean.ApprovalSteps;
import wh.fcfz.officecontroller.all.service.Impl.ApprovalFormsServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

@RestController
@RequestMapping("/approvalForms")
public class ApprovalFormsController {

    @Autowired
    private  ApprovalFormsServiceImpl approvalFormsService;

    @PostMapping("/addApprovalForms")
    public Result addApprovalForms(@RequestBody MyPage<ApprovalForms> approvalForms)
    {
        return approvalFormsService.getApprovalForms(approvalForms);
    }

    @PostMapping("/updateApprovalFroms")
    @SaCheckPermission("admin")
    public Result updateApprovalFroms(@RequestBody MyPage<ApprovalSteps> approvalSteps)
    {
        return approvalFormsService.updateApprovalForms(approvalSteps);
    }
}
