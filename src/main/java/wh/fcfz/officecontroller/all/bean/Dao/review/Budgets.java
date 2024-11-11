package wh.fcfz.officecontroller.all.bean.Dao.review;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("budgets")
public class Budgets {
@TableId(value = "budget_id",type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
  private Long budgetId;
  private String department;
  private Long amount;
  private String period;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
