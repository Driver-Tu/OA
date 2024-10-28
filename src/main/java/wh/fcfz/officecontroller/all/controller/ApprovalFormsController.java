package wh.fcfz.officecontroller.all.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalFormsDto;
import wh.fcfz.officecontroller.all.service.Impl.ApprovalFormsServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

@RestController
@RequestMapping("/approvalForms")
public class ApprovalFormsController {

    @Autowired
    private  ApprovalFormsServiceImpl approvalFormsService;

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

    @PostMapping("/updateApprovalFroms")
    @SaCheckPermission("admin")
    public Result updateApprovalFroms(@RequestBody MyPage<ApprovalSteps> approvalSteps)
    {
        return approvalFormsService.updateApprovalForms(approvalSteps);
    }
}
