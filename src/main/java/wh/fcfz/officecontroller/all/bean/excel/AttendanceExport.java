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
public class AttendanceExport {
    @ExcelProperty(value = "打卡记录ID",index = 0)
    private Integer attendanceId;
    @ExcelProperty(value = "打卡人姓名",index = 1)
    private String userName;
    @ExcelProperty(value = "打卡人部门",index = 2)
    private String departName;
    @ExcelProperty(value = "上班打卡时间",index = 3)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date timeIn;
    @ExcelProperty(value = "下班打卡时间",index = 4)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date timeOut;
    @ExcelProperty(value = "打卡时间日期",index = 5)
    @DateTimeFormat("yyyy年MM月dd日")
    private Date date;
    @ExcelProperty(value = "打卡状态",index = 6)
    private String status;
    @ExcelProperty(value = "打卡地址",index = 7)
    private String address;
    @ExcelProperty(value = "打卡类型",index = 8)
    private String type;
}
