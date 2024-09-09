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
@TableName("attendance")
public class Attendance {
@TableId(type = IdType.AUTO)
  private long attendanceId;
  private String attendanceUserId;
  private long timeIn;
  private java.sql.Timestamp timeOut;
  private String date;
  private String status;

}
