package wh.fcfz.officecontroller.all.mapper;

import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wh.fcfz.officecontroller.all.bean.domain.FormFieldAllDomain;

import java.util.List;

/**
* @author admin
* @description 针对表【form(表单通用 ( 字段 ) 表)】的数据库操作Mapper
* @createDate 2024-11-19 16:39:04
* @Entity wh.fcfz.officecontroller.all.bean.Dao.form.Form
*/
@Mapper
public interface FormMapper extends BaseMapper<Form> {
    //根据formId查询字段和字段值
     List<FormFieldAllDomain> getFormField(Integer formId);

}




