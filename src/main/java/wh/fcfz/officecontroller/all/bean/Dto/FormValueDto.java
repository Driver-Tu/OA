package wh.fcfz.officecontroller.all.bean.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormValueDto {

    /**
     * 表单列 ID
     */
    private Integer fieldId;

    /**
     * 字段的具体内容，使用 JSON 字符串存储，或存储富文本编辑器的文本内容
     */
    private Object fieldValue;
}
