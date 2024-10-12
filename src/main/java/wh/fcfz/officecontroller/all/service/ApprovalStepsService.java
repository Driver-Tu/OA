package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;
import wh.fcfz.officecontroller.all.tool.Result;

public interface ApprovalStepsService extends IService<ApprovalSteps> {
    //添加数据
    Result addApprovalSteps(ApprovalSteps approvalSteps);
}
