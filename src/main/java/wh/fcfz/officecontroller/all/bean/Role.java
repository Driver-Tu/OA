package wh.fcfz.officecontroller.all.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer roleId;
    private String roleName;
    private String permissions;
    private java.sql.Timestamp ctTime;
    private java.sql.Timestamp upTime;


}
