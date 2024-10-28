package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;
import wh.fcfz.officecontroller.all.mapper.ApprovalStepsMapper;
import wh.fcfz.officecontroller.all.service.ApprovalStepsService;

@Service
public class ApprovalStepsServiceImpl extends ServiceImpl<ApprovalStepsMapper, ApprovalSteps> implements ApprovalStepsService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApprovalSteps addApprovalSteps(ApprovalSteps approvalSteps) {
        return save(approvalSteps) ? approvalSteps : null;
    }
}
