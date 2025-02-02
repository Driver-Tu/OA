package wh.fcfz.officecontroller.all.mapper;

import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplateFieldOption;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wh.fcfz.officecontroller.all.bean.Vo.FieldOptionVo;

import java.util.List;

/**
* @author admin
* @description 针对表【form_template_field_option】的数据库操作Mapper
* @createDate 2024-11-19 15:50:09
* @Entity wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplateFieldOption
*/
@Mapper
public interface FormTemplateFieldOptionMapper extends BaseMapper<FormTemplateFieldOption> {

    List<FieldOptionVo> selecFormFieldOptionListByFormFieldId(Integer fieldId);
}




