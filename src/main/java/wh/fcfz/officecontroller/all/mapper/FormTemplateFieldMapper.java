package wh.fcfz.officecontroller.all.mapper;

import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplateField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【form_template_field(表单字段模板表)】的数据库操作Mapper
* @createDate 2024-11-19 15:50:09
* @Entity wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplateField
*/
@Mapper
public interface FormTemplateFieldMapper extends BaseMapper<FormTemplateField> {

}




