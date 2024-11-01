package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalSteps;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalFormsDto;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalStepsDto;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalFormsVo;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalStepsVo;
import wh.fcfz.officecontroller.all.mapper.ApprovalFormsMapper;
import wh.fcfz.officecontroller.all.mapper.ApprovalStepsMapper;
import wh.fcfz.officecontroller.all.service.ApprovalStepsService;
import wh.fcfz.officecontroller.all.tool.MyPage;

import java.util.List;

@Service
public class ApprovalStepsServiceImpl extends ServiceImpl<ApprovalStepsMapper, ApprovalSteps> implements ApprovalStepsService {
    @Autowired
    private ApprovalStepsMapper approvalStepsMapper;
    @Autowired
    private ApprovalFormsMapper approvalFormsMapper;

    @Autowired
    private @Lazy ApprovalFormsServiceImpl approvalFormsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApprovalSteps addApprovalSteps(ApprovalSteps approvalSteps) {
        return save(approvalSteps) ? approvalSteps : null;
    }

    @Override
    @Transactional
    public Page<ApprovalStepsVo> getApprovalSteps(MyPage<ApprovalStepsDto> approvalStepsDto) {
        List<ApprovalSteps> approvalSteps = approvalStepsMapper
                .selectList(new LambdaQueryWrapper<ApprovalSteps>()
                        .eq(ApprovalSteps::getApprover, StpUtil.getLoginIdAsInt())
                        .eq(approvalStepsDto.getData().getStatus()!=null,ApprovalSteps::getResult, approvalStepsDto.getData().getStatus())
                        .orderByDesc(ApprovalSteps::getStepId));
        List<ApprovalStepsVo> list = approvalSteps.stream().map(approvalStep -> {
            ApprovalStepsVo approvalStepsVo = new ApprovalStepsVo();
            BeanUtil.copyProperties(approvalStep, approvalStepsVo);
            ApprovalFormsDto approvalFormsDto = new ApprovalFormsDto();
            approvalFormsDto.setFormId(approvalStep.getFormId());
            List<ApprovalFormsVo> approvalFormsVos = approvalFormsMapper.getList(approvalFormsDto);
            List<ApprovalFormsVo> approvalFormsVoList =approvalFormsService.GetList(approvalFormsVos);
            approvalStepsVo.setApprovalForms(approvalFormsVoList.get(0));
            return approvalStepsVo;
        }).toList();
        Page<ApprovalStepsVo> page = new Page<>(approvalStepsDto.getPageNum(), approvalStepsDto.getPageSize());
        if(approvalStepsDto.getData().getType()!=null){
            list = list.stream().filter(approvalStepsVo -> approvalStepsVo.getApprovalForms().getType().equals(approvalStepsDto.getData().getType())).toList();
        }
        List<ApprovalStepsVo> collect = list.stream().skip((long) (approvalStepsDto.getPageNum() - 1)).limit(approvalStepsDto.getPageSize()).toList();
        page.setRecords(collect);
        page.setTotal(list.size());
        return page;
    }

    @Override
    public boolean deleteApprovalSteps(Long id) {
        if(approvalStepsMapper.delete(new LambdaQueryWrapper<ApprovalSteps>().eq(ApprovalSteps::getFormId,id))>0){
         return true;
        }else {
            throw new RuntimeException("删除失败");
        }
    }
}
