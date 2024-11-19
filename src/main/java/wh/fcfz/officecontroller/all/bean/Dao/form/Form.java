package wh.fcfz.officecontroller.all.bean.Dao.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
    private Integer id;

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
     * 表单备注
     */
    private String remark;

    /**
     * 创建者
     */
    private Integer createdAt;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新者
     */
    private Integer updatedAt;

    /**
     * 更新时间
     */
    private Date updatedTime;

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
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFormName() == null ? other.getFormName() == null : this.getFormName().equals(other.getFormName()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getFormRouter() == null ? other.getFormRouter() == null : this.getFormRouter().equals(other.getFormRouter()))
            && (this.getIsScheduled() == null ? other.getIsScheduled() == null : this.getIsScheduled().equals(other.getIsScheduled()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
            && (this.getSubmitTime() == null ? other.getSubmitTime() == null : this.getSubmitTime().equals(other.getSubmitTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFormName() == null) ? 0 : getFormName().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getFormRouter() == null) ? 0 : getFormRouter().hashCode());
        result = prime * result + ((getIsScheduled() == null) ? 0 : getIsScheduled().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", formName=").append(formName);
        sb.append(", templateId=").append(templateId);
        sb.append(", formRouter=").append(formRouter);
        sb.append(", isScheduled=").append(isScheduled);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", submitTime=").append(submitTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}