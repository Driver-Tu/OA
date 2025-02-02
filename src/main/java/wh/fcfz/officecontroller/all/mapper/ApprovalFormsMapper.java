package wh.fcfz.officecontroller.all.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalForms;
import wh.fcfz.officecontroller.all.bean.Dao.File;
import wh.fcfz.officecontroller.all.bean.Dto.ApprovalFormsDto;
import wh.fcfz.officecontroller.all.bean.Vo.ApprovalFormsVo;

import java.util.List;

@Mapper
public interface ApprovalFormsMapper extends BaseMapper<ApprovalForms> {
    List<ApprovalFormsVo> getList(ApprovalFormsDto approvalFormsDto);

    List<File> getFileList(Long formId);

    //查询step表中完成或者未完成的审批有几个，排除掉没有审批
    int getApprovalStepsCount(Long formId);
}
