package wh.fcfz.officecontroller.all.bean.Dao.form;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表单字段模板表
 * @TableName form_template_field
 */
@TableName(value ="form_template_field", autoResultMap = true)
@Data
public class FormTemplateField implements Serializable {
    /**
     * 主键，字段模板 ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer fieldId;

    /**
     * 表单类型 ID，关联 sys_form_type 表
     */
    private Integer templateId;

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
    @TableField(value = "validation_rule", typeHandler = JacksonTypeHandler.class)
    private JSONArray validationRule;

    /**
     * 是否为单选框和多选框（input为输入框 radio为单选框/单选列表 checkbox为多选框/多选列表 date_picker为日期选择器 datetime_piker为日期时间选择器）
     */
    private String formItemType;

    /**
     * 字段的默认值
     */
    private String defaultValue;

    private Integer isSummary;

    private Integer isVisible;

    /**
     * 创建部门
     */
    private Integer createDept;

    /**
     * 创建者
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 修改时间
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
        FormTemplateField other = (FormTemplateField) that;
        return (this.getFieldId() == null ? other.getFieldId() == null : this.getFieldId().equals(other.getFieldId()))
            && (this.getTemplateId() == null ? other.getTemplateId() == null : this.getTemplateId().equals(other.getTemplateId()))
            && (this.getFieldTitle() == null ? other.getFieldTitle() == null : this.getFieldTitle().equals(other.getFieldTitle()))
            && (this.getFieldType() == null ? other.getFieldType() == null : this.getFieldType().equals(other.getFieldType()))
            && (this.getValidationRule() == null ? other.getValidationRule() == null : this.getValidationRule().equals(other.getValidationRule()))
            && (this.getFormItemType() == null ? other.getFormItemType() == null : this.getFormItemType().equals(other.getFormItemType()))
            && (this.getDefaultValue() == null ? other.getDefaultValue() == null : this.getDefaultValue().equals(other.getDefaultValue()))
            && (this.getCreateDept() == null ? other.getCreateDept() == null : this.getCreateDept().equals(other.getCreateDept()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getFieldId() == null) ? 0 : this.getFieldId().hashCode());
        result = prime * result + ((getTemplateId() == null) ? 0 : getTemplateId().hashCode());
        result = prime * result + ((getFieldTitle() == null) ? 0 : getFieldTitle().hashCode());
        result = prime * result + ((getFieldType() == null) ? 0 : getFieldType().hashCode());
        result = prime * result + ((getValidationRule() == null) ? 0 : getValidationRule().hashCode());
        result = prime * result + ((getFormItemType() == null) ? 0 : getFormItemType().hashCode());
        result = prime * result + ((getDefaultValue() == null) ? 0 : getDefaultValue().hashCode());
        result = prime * result + ((getCreateDept() == null) ? 0 : getCreateDept().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", filedId=").append(fieldId);
        sb.append(", templateId=").append(templateId);
        sb.append(", fieldTitle=").append(fieldTitle);
        sb.append(", fieldType=").append(fieldType);
        sb.append(", validationRule=").append(validationRule);
        sb.append(", formItemType=").append(formItemType);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", createDept=").append(createDept);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}