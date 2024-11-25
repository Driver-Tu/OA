package wh.fcfz.officecontroller.all.bean.Dao.form;

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
 * @TableName form_field_value
 */
@Data
@TableName(value ="form_field_value")
@AllArgsConstructor
@NoArgsConstructor
public class FormFieldValue implements Serializable {
    /**
     * 表单列数据值 ID
     */
    @TableId(type = IdType.AUTO)
    Integer id;

    /**
     * 表单 ID
     */
    private Integer formId;

    /**
     * 表单列 ID
     */
    private Integer fieldId;

    /**
     * 字段的具体内容，使用 JSON 字符串存储，或存储富文本编辑器的文本内容
     */
    private String fieldValue;

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

}