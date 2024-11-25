package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormFieldVo {

    /**
     * 字段值id
     */
    private Integer fieldId;

    /**
     * 字段名称，存储具体的字段
     */
    private String fieldTitle;

    /**
     * 字段类型，如字符串、整数、日期等
     */
    private String fieldType;

    /**
     * 排序字段
     */
    private Integer fieldSort;

    /**
     * 字段验证规则，存储验证规则如 max:255 等
     */
    private String validationRule;

    /**
     * 是否必填，1 表示必填，0 表示非必填
     */
    private Integer isRequired;

    /**
     * 是否为单选框和多选框（input为输入框 radio为单选框/单选列表 checkbox为多选框/多选列表 date_picker为日期选择器 datetime_piker为日期时间选择器）
     */
    private String formItemType;

    /**
     * 字段的默认值
     */
    private String defaultValue;

    /**
     * 字段的选项
     */
    private List<FieldOptionVo> fieldOptions;
}
