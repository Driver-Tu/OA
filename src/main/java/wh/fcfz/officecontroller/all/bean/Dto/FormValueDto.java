package wh.fcfz.officecontroller.all.bean.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormValueDto {

    private Integer id;

    /**
     * 表单列 ID
     */
    private Integer fieldId;

    /**
     * 表单名称
     */
    private String fieldTitle;

    /**
     * 字段的具体内容，使用 JSON 字符串存储，或存储富文本编辑器的文本内容
     */
    private Object fieldValue;
}
