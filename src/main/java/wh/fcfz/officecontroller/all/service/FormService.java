package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import wh.fcfz.officecontroller.all.bean.Dto.FormDto;
import wh.fcfz.officecontroller.all.bean.Vo.FormValueVo;
import wh.fcfz.officecontroller.all.bean.Vo.FormVo;
import wh.fcfz.officecontroller.all.tool.MyPage;

import java.util.List;

/**
* @author admin
* @description 针对表【form(表单通用 ( 字段 ) 表)】的数据库操作Service
* @createDate 2024-11-19 16:39:04
*/
public interface FormService extends IService<Form> {

    List<Form> getAllForms(MyPage<Form> data);

    FormValueVo getFormById(@PathVariable("id") Integer id);

    @Transactional
    void addForm(@RequestBody FormDto formDto);

    @Transactional
    void updateForm(@RequestBody FormDto formDto);

    Page<FormVo> selectPage(MyPage<FormDto> page);
}
