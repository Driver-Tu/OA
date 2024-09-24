package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.ApprovalForms;
import wh.fcfz.officecontroller.all.mapper.ApprovalFormsMapper;
import wh.fcfz.officecontroller.all.service.ApprovalFormsService;
import wh.fcfz.officecontroller.all.tool.Result;

@Service
public class ApprovalFormsServiceImpl extends ServiceImpl<ApprovalFormsMapper, ApprovalForms> implements ApprovalFormsService {

    @Autowired
    private ApprovalFormsMapper approvalFormsMapper;
    //添加审批数据
    @Override
    public Result addApprovalForms(ApprovalForms approvalForms) {
        if (approvalFormsMapper.insert(approvalForms)>0){
            return new Result("200","添加成功",null);
        }
        return null;
    }
}
