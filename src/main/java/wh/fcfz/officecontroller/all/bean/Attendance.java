package wh.fcfz.officecontroller.all.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("attendance")
public class Attendance {
  @TableId(type = IdType.AUTO)
  private Integer attendanceId; // 考勤ID

  private String attendanceUserId; // 考勤人ID

  private Date timeIn; // 上班打卡时间

  private Date timeOut; // 下班打卡时间

  private String date; // 打卡日期

  private String status; // 打卡状态

  private String address; // 打卡地址

  private BigDecimal longitude; // 经度

  private BigDecimal latitude; // 纬度

  private String type; // 打卡类型
}
