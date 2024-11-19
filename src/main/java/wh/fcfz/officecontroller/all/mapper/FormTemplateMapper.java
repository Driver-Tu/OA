package wh.fcfz.officecontroller.all.mapper;

import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【form_template(表单类型表)】的数据库操作Mapper
* @createDate 2024-11-19 11:28:14
* @Entity wh.fcfz.officecontroller.all.bean.Dao.form.FormTemplate
*/
@Mapper
public interface FormTemplateMapper extends BaseMapper<FormTemplate> {

}




