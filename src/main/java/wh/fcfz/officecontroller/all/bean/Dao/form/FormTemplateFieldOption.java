package wh.fcfz.officecontroller.all.bean.Dao.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName form_template_field_option
 */
@TableName(value ="form_template_field_option")
@Data
public class FormTemplateFieldOption implements Serializable {
    /**
     * 表单模板列的可选项 ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 表单模板列 ID
     */
    private Long templateFieldId;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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
        FormTemplateFieldOption other = (FormTemplateFieldOption) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTemplateFieldId() == null ? other.getTemplateFieldId() == null : this.getTemplateFieldId().equals(other.getTemplateFieldId()))
            && (this.getOptionValue() == null ? other.getOptionValue() == null : this.getOptionValue().equals(other.getOptionValue()))
            && (this.getOptionLabel() == null ? other.getOptionLabel() == null : this.getOptionLabel().equals(other.getOptionLabel()))
            && (this.getOprionSort() == null ? other.getOprionSort() == null : this.getOprionSort().equals(other.getOprionSort()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTemplateFieldId() == null) ? 0 : getTemplateFieldId().hashCode());
        result = prime * result + ((getOptionValue() == null) ? 0 : getOptionValue().hashCode());
        result = prime * result + ((getOptionLabel() == null) ? 0 : getOptionLabel().hashCode());
        result = prime * result + ((getOprionSort() == null) ? 0 : getOprionSort().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateFieldId=").append(templateFieldId);
        sb.append(", optionValue=").append(optionValue);
        sb.append(", optionLabel=").append(optionLabel);
        sb.append(", oprionSort=").append(oprionSort);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}