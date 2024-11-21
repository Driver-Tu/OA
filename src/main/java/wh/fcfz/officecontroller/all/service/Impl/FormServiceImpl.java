package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;
import wh.fcfz.officecontroller.all.service.FormService;
import wh.fcfz.officecontroller.all.mapper.FormMapper;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.tool.MyPage;

import java.util.List;

/**
* @author admin
* @description 针对表【form(表单通用 ( 字段 ) 表)】的数据库操作Service实现
* @createDate 2024-11-19 16:39:04
*/
@Service
public class FormServiceImpl extends ServiceImpl<FormMapper, Form>
    implements FormService{

    private final FormMapper formMapper;

    public FormServiceImpl(FormMapper formMapper) {
        this.formMapper = formMapper;
    }

    @Override
    public List<Form> getAllForms(MyPage<Form> data) {
        Page<Form> page=new Page<>(data.getPageNum(),data.getPageSize());
        formMapper.selectPage(page,new LambdaQueryWrapper<Form>()
                .eq(Form::getStatus,data.getData().getStatus()));
        return page.getRecords();
    }
}




