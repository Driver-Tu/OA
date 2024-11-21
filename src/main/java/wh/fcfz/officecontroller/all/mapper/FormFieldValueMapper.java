package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormFieldValue;
import wh.fcfz.officecontroller.all.bean.Vo.FormFieldValueVo;

import java.util.List;

/**
* @author zds
* @description 针对表【form_field_value】的数据库操作Mapper
* @createDate 2024-11-20 10:51:29
* @Entity wh.fcfz.officecontroller.all.bean.Dao.form.FormFieldValue
*/
@Mapper
public interface FormFieldValueMapper extends BaseMapper<FormFieldValue> {

    public List<FormFieldValueVo> selecFormFieldValueListByFormId(Integer formId);

}




