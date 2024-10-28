package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("leave_requests")
public class LeaveRequests {
@TableId(value = "leave_request_id", type = IdType.AUTO)
  private Long leaveRequestId;
  private Long employeeId;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private String reason;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
