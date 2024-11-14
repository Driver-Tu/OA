package wh.fcfz.officecontroller.all.bean.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知推送表
 * @TableName notification_push
 */
@TableName(value ="notification_push")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPush implements Serializable {
    /**
     * 通知ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 通知类型（如公告、审批状态、日志变更等）
     */
    private String notificationType;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 通知状态（待处理、已发送、发送失败）
     */
    private Object status;

    /**
     * 创建者（用于记录通知来源或用户）
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 接收人ID，多个用户以逗号分隔
     */
    private String recipientIds;

    /**
     * 扩展数据（可以用来存储额外的信息，如通知的附件等）
     */
    private String extraData;

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
        NotificationPush other = (NotificationPush) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNotificationType() == null ? other.getNotificationType() == null : this.getNotificationType().equals(other.getNotificationType()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRecipientIds() == null ? other.getRecipientIds() == null : this.getRecipientIds().equals(other.getRecipientIds()))
            && (this.getExtraData() == null ? other.getExtraData() == null : this.getExtraData().equals(other.getExtraData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNotificationType() == null) ? 0 : getNotificationType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRecipientIds() == null) ? 0 : getRecipientIds().hashCode());
        result = prime * result + ((getExtraData() == null) ? 0 : getExtraData().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", notificationType=").append(notificationType);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", recipientIds=").append(recipientIds);
        sb.append(", extraData=").append(extraData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}