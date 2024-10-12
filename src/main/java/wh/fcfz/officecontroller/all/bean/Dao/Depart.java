package wh.fcfz.officecontroller.all.bean.Dao;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("depart")
public class Depart {
@TableId(type = IdType.AUTO)
  private Integer departId;
  private String departName;
  private String departTelephone;
  private String departEmail;
  private String departMessage;
  private java.sql.Timestamp ctTime;
  private java.sql.Timestamp upTime;
  private Integer status;

  @TableField(exist = false)
  private Integer employeeCount;
}
