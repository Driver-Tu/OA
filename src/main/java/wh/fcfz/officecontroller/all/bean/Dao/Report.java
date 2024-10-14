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
@TableName("report")
public class Report {
    @TableId(value = "report_id", type = IdType.AUTO)
    private long reportId;
    private long reportUserId;
    private String reportName;
    private String type;
    private java.sql.Timestamp reportDate;
    private java.sql.Timestamp upDate;
    private java.sql.Timestamp ctDate;
    private String filePath;
    private String content;
}
