package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;
import wh.fcfz.officecontroller.all.mapper.ApprovalStepsMapper;
import wh.fcfz.officecontroller.all.service.ApprovalStepsService;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

@Service
public class ApprovalStepsServiceImpl extends ServiceImpl<ApprovalStepsMapper, ApprovalSteps> implements ApprovalStepsService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addApprovalSteps(ApprovalSteps approvalSteps) {
        if(approvalSteps==null){
            return new Result(ResponseEnum.DATA_NOT_EXIST,null);
        }
        try {
            if(baseMapper.insert(approvalSteps)>0){
                return new Result(ResponseEnum.SUCCESS,null);
            }else {
                return new Result(ResponseEnum.INSERT_SERVER_ERROR,null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
