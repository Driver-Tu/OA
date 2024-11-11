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
import wh.fcfz.officecontroller.all.bean.Dto.UserDto;
import wh.fcfz.officecontroller.all.bean.Vo.UserVo;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.Impl.ApprovalFormsServiceImpl;
import wh.fcfz.officecontroller.all.service.Impl.ApprovalStepsServiceImpl;
import wh.fcfz.officecontroller.all.tool.MyException;
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
    @Transactional
    public Result updateApprovalFroms(@RequestBody  ApprovalSteps approvalSteps)
    {
        if(approvalSteps.getStepId()==null){
            return new Result(ResponseEnum.DATA_NOT_EXIST,null);
        }
        ApprovalSteps byId = approvalStepsServiceImpl.getById(approvalSteps.getStepId());
        if(byId.getApprover()!=StpUtil.getLoginIdAsInt()){
            throw new MyException("权限不足","10305");
        }
        ApprovalForms approvalForms = approvalFormsService.getById(byId.getFormId());
        //修改审批步骤表
        int i = approvalStepsServiceImpl.updateApprovalSteps(approvalForms, byId);
        //修改审批信息表
        int j= approvalFormsService.updateApprovalForms(byId.getResult(), approvalForms);
        if(i>0&&j>0){
            return new Result(ResponseEnum.SUCCESS,true);
        }else {
            throw new MyException("审批失败","10707");
        }
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
        approvers.forEach(approver ->{
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
    public Result deleteAppForms(@RequestBody List<Long> ids) {
        ApprovalForms byId;
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
                if(b1){
                    approvalStepsServiceImpl.deleteApprovalSteps(id);
                }
            } catch (Exception e) {
                throw new MyException("删除失败","10703");
            }
        }
        return new Result(ResponseEnum.SUCCESS,true);
    }

    @Autowired
    private UserMapper userMapper;
    /**
     * 指定审批人
     */
    @GetMapping("/setApprovers")
    @Transactional
    public Result SelectApprovers() {
        /**
         * 查询每个用户的权限和信息
         */
        UserDto userDto = new UserDto();
        userDto.setRoleName("admin");
        userDto.setDepartName(userMapper.selectDepartName(userMapper.selectById(StpUtil.getLoginIdAsInt()).getDepartmentId()));
        List<UserVo> userVos = userMapper.selectUserList(userDto);

        return new Result<>(ResponseEnum.SUCCESS, userVos);
    }
}
