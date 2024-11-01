package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalStepsDto;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalStepsVo;
import wh.fcfz.officecontroller.all.tool.MyPage;

public interface ApprovalStepsService extends IService<ApprovalSteps> {
    //添加数据
    ApprovalSteps addApprovalSteps(ApprovalSteps approvalSteps);
    //查询审批信息
    Page<ApprovalStepsVo> getApprovalSteps(MyPage<ApprovalStepsDto> approvalSteps);
    //查询审批删除信息
    boolean deleteApprovalSteps(Long id);
}
