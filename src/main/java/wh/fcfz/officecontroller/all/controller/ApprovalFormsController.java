package wh.fcfz.officecontroller.all.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

@Slf4j
@RestController
@RequestMapping("/approvalForms")
public class ApprovalFormsController {

    @Autowired
    private  ApprovalFormsServiceImpl approvalFormsService;
    @Autowired
    private ApprovalStepsServiceImpl approvalStepsServiceImpl;
    @Autowired
    private ApprovalFormsServiceImpl approvalFormsServiceImpl;

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
    public Result addApprovalForms(@RequestBody AddApprovalFormsDto addApprovalFormsDto)
    {
       if(addApprovalFormsDto.getApprovalForms()==null){
           return new Result(ResponseEnum.DATA_NOT_EXIST,null);
       }

        Long id = approvalFormsServiceImpl.setDetail(addApprovalFormsDto);
        addApprovalFormsDto.getApprovalForms().setAllId(id);
        ApprovalForms AddApprovalForms = approvalFormsService.addApprovalForms(addApprovalFormsDto.getApprovalForms());
        List<Integer> approvers = addApprovalFormsDto.getApprovers();
        approvers.stream().forEach(approver ->{
                ApprovalSteps approvalSteps = new ApprovalSteps();
                approvalSteps.setFormId(AddApprovalForms.getFormId());
                approvalSteps.setApprover(approver);
                approvalSteps.setResult("待审批");
                ApprovalSteps AddApprovalStep = approvalStepsServiceImpl.addApprovalSteps(approvalSteps);
        });

        return new Result(ResponseEnum.SUCCESS,true);
    }

    @DeleteMapping("/deleteAppForms")
    @Transactional
    public Result deleteAppForms(@RequestBody List<Long> ids)
    {
        ApprovalForms byId=new ApprovalForms();
        for (Long id : ids) {
            byId= approvalFormsService.getById(id);
            if(byId==null){
                return new Result(ResponseEnum.DATA_NOT_EXIST,"删除的数据中有非审批数据");
            }
            if(byId.getStatus().equals("已完成")){
                return new Result(ResponseEnum.DATA_NOT_EXIST,"删除的数据中有审批完成的数据:"+byId.getFromName());
            }
            if (byId.getApplicantId()!=StpUtil.getLoginIdAsInt()){
                return new Result(ResponseEnum.DATA_NOT_EXIST,"删除的数据中有非本人提交的数据:"+byId.getFromName());
            }
            try {
                boolean b1 = approvalFormsService.deleteApprovalForms(id);
                boolean b2 = approvalStepsServiceImpl.deleteApprovalSteps(id);
            } catch (Exception e) {
                throw new RuntimeException("删除失败");
            }
        }
        return new Result(ResponseEnum.SUCCESS,true);
    }
}
