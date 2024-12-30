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
 * 
 * @TableName scheduling
 */
@TableName(value ="scheduling")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scheduling implements Serializable {
    /**
     * 排班规则id
     */
    @TableId(type = IdType.AUTO)
    private Integer schedulingId;

    /**
     * 排班规则名称
     */
    private String schedulingName;

    /**
     * 部门id
     */
    private String departId;

    /**
     * 排班月份
     */
    private String date;

    /**
     * 规则
     */
    private String rule;

    /**
     * 创建时间更新
     */
    private Date ctTime;

    /**
     * 更新时间
     */
    private Date upTime;

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
        Scheduling other = (Scheduling) that;
        return (this.getSchedulingId() == null ? other.getSchedulingId() == null : this.getSchedulingId().equals(other.getSchedulingId()))
            && (this.getSchedulingName() == null ? other.getSchedulingName() == null : this.getSchedulingName().equals(other.getSchedulingName()))
            && (this.getDepartId() == null ? other.getDepartId() == null : this.getDepartId().equals(other.getDepartId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getRule() == null ? other.getRule() == null : this.getRule().equals(other.getRule()))
            && (this.getCtTime() == null ? other.getCtTime() == null : this.getCtTime().equals(other.getCtTime()))
            && (this.getUpTime() == null ? other.getUpTime() == null : this.getUpTime().equals(other.getUpTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSchedulingId() == null) ? 0 : getSchedulingId().hashCode());
        result = prime * result + ((getSchedulingName() == null) ? 0 : getSchedulingName().hashCode());
        result = prime * result + ((getDepartId() == null) ? 0 : getDepartId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getRule() == null) ? 0 : getRule().hashCode());
        result = prime * result + ((getCtTime() == null) ? 0 : getCtTime().hashCode());
        result = prime * result + ((getUpTime() == null) ? 0 : getUpTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", schedulingId=").append(schedulingId);
        sb.append(", schedulingName=").append(schedulingName);
        sb.append(", departId=").append(departId);
        sb.append(", date=").append(date);
        sb.append(", rule=").append(rule);
        sb.append(", ctTime=").append(ctTime);
        sb.append(", upTime=").append(upTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}