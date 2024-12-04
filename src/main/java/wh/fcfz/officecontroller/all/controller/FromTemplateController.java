package wh.fcfz.officecontroller.all.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalForReviewVo;
import wh.fcfz.officecontroller.all.service.FormTemplateService;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

@RestController
@RequestMapping("/formTemplate")
public class FromTemplateController {


    private final FormTemplateService formTemplateService;

    public FromTemplateController(FormTemplateService formTemplateService) {
        this.formTemplateService = formTemplateService;
    }

    /**
     *获取审批模板
     * @return
     */
    @GetMapping("/getSysTemplateList")
    public Result<List<ApprovalForReviewVo>> getSysTemplateList(){
        return new Result<>(ResponseEnum.SUCCESS,formTemplateService.selectApprovalForReview());
    }

}
