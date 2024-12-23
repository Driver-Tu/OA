package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormInstanceVo {

    /**
     * 表单通用表主键
     */
    private Integer formId;

    /**
     * 表单名称
     */
    private String formName;

    /**
     * 表单类型 id 外键
     */
    private Integer templateId;

    /**
     * 表单路由地址
     */
    private String formRouter;

    /**
     * 是否开启定时发送（0否 1是）
     */
    private String isScheduled;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 表单状态（0 草稿，1 已提交）
     */
    private String status;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交时间
     */
    private Date submitTime;

    private List<FormFieldValueVo> formFieldValues;
}
