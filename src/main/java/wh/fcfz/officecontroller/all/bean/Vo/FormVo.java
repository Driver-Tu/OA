package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.form.Form;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormVo {

    private Form form;

    private List<FormFieldValueVo> formFieldValues;
}
