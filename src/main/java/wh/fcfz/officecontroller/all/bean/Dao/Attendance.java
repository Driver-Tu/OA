package wh.fcfz.officecontroller.all.bean.Dao;


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
@TableId(value = "attendance_id",type = IdType.AUTO)
  private Integer attendanceId;
  private Integer attendanceUserId;
  private java.sql.Timestamp timeIn;
  private java.sql.Timestamp timeOut;
  private java.sql.Date date;
  private String status;
  private String address;
  private double longitude;
  private double latitude;
  private String type;

  public Attendance(Attendance attendance) {
  }
}
