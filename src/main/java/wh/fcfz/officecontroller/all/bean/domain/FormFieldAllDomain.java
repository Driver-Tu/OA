package wh.fcfz.officecontroller.all.bean.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormFieldAllDomain {
    private Integer fieldId;
    private String fieldName;
    private String fieldValue;
}
