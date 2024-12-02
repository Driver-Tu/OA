package wh.fcfz.officecontroller.all.bean.Dao.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表单通用 ( 字段 ) 表
 * @TableName form
 */
@TableName(value ="form")
@Data
public class Form implements Serializable {
    /**
     * 表单通用表主键
     */
    @TableId(type = IdType.AUTO)
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
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交时间
     */
    private Date submitTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Form other = (Form) that;
        return (this.getFormId() == null ? other.getFormId() == null : this.getFormId().equals(other.getFormId()))
            && (this.getFormName() == null ? other.getFormName() == null : this.getFormName().equals(other.getFormName()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getFormRouter() == null ? other.getFormRouter() == null : this.getFormRouter().equals(other.getFormRouter()))
            && (this.getIsScheduled() == null ? other.getIsScheduled() == null : this.getIsScheduled().equals(other.getIsScheduled()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getSubmitTime() == null ? other.getSubmitTime() == null : this.getSubmitTime().equals(other.getSubmitTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFormId() == null) ? 0 : getFormId().hashCode());
        result = prime * result + ((getFormName() == null) ? 0 : getFormName().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getFormRouter() == null) ? 0 : getFormRouter().hashCode());
        result = prime * result + ((getIsScheduled() == null) ? 0 : getIsScheduled().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((this.getCreateBy() == null) ? 0 : this.getCreateBy().hashCode());
        result = prime * result + ((this.getCreateTime() == null) ? 0 : this.getCreateTime().hashCode());
        result = prime * result + ((this.getUpdateBy() == null) ? 0 : this.getUpdateBy().hashCode());
        result = prime * result + ((this.getUpdateTime() == null) ? 0 : this.getUpdateTime().hashCode());
        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", formId=").append(formId);
        sb.append(", formName=").append(formName);
        sb.append(", templateId=").append(templateId);
        sb.append(", formRouter=").append(formRouter);
        sb.append(", isScheduled=").append(isScheduled);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", submitTime=").append(submitTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}