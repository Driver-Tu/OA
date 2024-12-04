package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldOptionVo {

    /**
     * 表单模板列的可选项 ID
     */
    private Integer id;

    /**
     * 表单模板列 ID
     */
    private Integer templateFieldId;

    /**
     * 可选项值
     */
    private String optionValue;

    /**
     * 可选项标签
     */
    private String optionLabel;

    /**
     * 可选项排序
     */
    private Integer oprionSort;
}
