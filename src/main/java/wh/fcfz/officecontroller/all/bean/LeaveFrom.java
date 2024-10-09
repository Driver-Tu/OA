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
@TableName("leave_form")
public class LeaveFrom {
@TableId(type = IdType.AUTO)
  private Integer LeaveId;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private String filePath;
}
