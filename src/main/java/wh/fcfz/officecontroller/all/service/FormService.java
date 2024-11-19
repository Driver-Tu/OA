package wh.fcfz.officecontroller.all.service;

import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.tool.MyPage;

import java.util.List;

/**
* @author admin
* @description 针对表【form(表单通用 ( 字段 ) 表)】的数据库操作Service
* @createDate 2024-11-19 16:39:04
*/
public interface FormService extends IService<Form> {

    List<Form> getAllForms(MyPage<Form> data);
}
