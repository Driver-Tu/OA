package wh.fcfz.officecontroller.all.mapper;

import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【form(表单通用 ( 字段 ) 表)】的数据库操作Mapper
* @createDate 2024-11-19 16:39:04
* @Entity wh.fcfz.officecontroller.all.bean.Dao.form.Form
*/
@Mapper
public interface FormMapper extends BaseMapper<Form> {

}




