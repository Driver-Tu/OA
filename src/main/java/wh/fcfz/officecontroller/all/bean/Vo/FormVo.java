package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormVo {

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

    private String content;

    /**
     * 表单状态（0 草稿，1 已提交）
     */
    private String status;

    /**
     * 表单备注
     */
    private String remark;

    /**
     * 创建者
     */
//    private Integer createBy;

    /**
     * 创建时间
     */
//    private Date createTime;

    /**
     * 更新者
     */
//    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交时间
     */
    private Date submitTime;


}
