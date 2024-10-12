package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalForms;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;
import wh.fcfz.officecontroller.all.tool.MyPage;
import wh.fcfz.officecontroller.all.tool.Result;

public interface ApprovalFormsService extends IService<ApprovalForms> {
    /**
     * 分页查询审批数据
     * */
    Result getApprovalForms(MyPage<ApprovalForms> myPage);

    Result getSelfApprovalForms(MyPage<ApprovalForms> myPage);
    /**
     * 插入审批数据
     * */
    Result addApprovalForms(ApprovalForms approvalForms);
    /**
     * 修改审批数据
     * */
    Result updateApprovalForms(MyPage<ApprovalSteps> myPage);
}
