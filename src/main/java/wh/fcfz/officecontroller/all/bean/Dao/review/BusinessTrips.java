package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("business_trips")
public class BusinessTrips {
@TableId(value = "business_trip_id",type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
  private Long businessTripId;
  private Long employeeId;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private String destination;
  private String purpose;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
