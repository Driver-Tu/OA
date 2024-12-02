package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormTemplateVo {

    /**
     * 表单类型 id 外键
     */
    private Integer templateId;

    /**
     * 表单类型名称，report 代表报告表单
     */
    private String templateName;

    /**
     * 是否为系统表单模板（1是 0否）
     */
    private String isSys;

    /**
     * 模版类型（leave 代表请假 business_trip 代表出差 ）
     */
    private String templateType;

    /**
     * 分类名称（行政，人事，财务）
     */
    private String category;

    /**
     * 模板状态（0开启 1关闭）
     */
    private String status;

    private Pattern testPattern;

    /**
     * 更新时间
     */
    private Date updatedTime;

    private List<TemplateFieldVo> templateFields;
}
