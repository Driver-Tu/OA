package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Dao.form.FormFieldValue;
import wh.fcfz.officecontroller.all.mapper.FormFieldValueMapper;
import wh.fcfz.officecontroller.all.service.FormFieldValueService;

@Service
public class FormFieldValueServiceImpl extends ServiceImpl<FormFieldValueMapper, FormFieldValue>implements FormFieldValueService {

}
