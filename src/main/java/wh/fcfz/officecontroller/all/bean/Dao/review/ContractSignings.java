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
@TableName("contract_signings")
public class ContractSignings {
@TableId(value = "contract_signing_id",type = IdType.AUTO)
  private Long contractSigningId;
  private String contractType;
  private java.sql.Date startDate;
  private java.sql.Date endDate;
  private double amount;
  private String status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
