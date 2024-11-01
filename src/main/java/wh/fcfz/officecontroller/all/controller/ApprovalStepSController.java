package wh.fcfz.officecontroller.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalStepsDto;
import wh.fcfz.officecontroller.all.service.ApprovalStepsService;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

@RestController
@RequestMapping("/approvalStep")
public class ApprovalStepSController {
    @Autowired
    private ApprovalStepsService approvalStepsService;

    //获取数据
    @PostMapping("/getApprovalSteps")
    public Result getApprovalSteps(@RequestBody MyPage<ApprovalStepsDto> approvalSteps)
    {
        return new Result(ResponseEnum.SUCCESS,approvalStepsService.getApprovalSteps(approvalSteps));
    }
}
