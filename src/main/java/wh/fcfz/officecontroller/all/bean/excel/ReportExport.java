package wh.fcfz.officecontroller.all.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportExport {
    @ExcelProperty(value = "日报id", index = 0)
    private Integer reportId;
    @ExcelProperty(value = "报告人姓名",index = 1)
    private String userName;
    @ExcelProperty(value = "报告人部门",index = 2)
    private String departName;
    @ExcelProperty(value = "报告类型",index = 3)
    private String type;
    @ExcelProperty(value = "报告标题",index = 4)
    private String reportName;
    @ExcelProperty(value = "报告内容",index = 5)
    private String content;
    @ExcelProperty(value = "报告时间",index = 6)
    @DateTimeFormat("yyyy年MM月dd日")
    private Date reportDate;
}
