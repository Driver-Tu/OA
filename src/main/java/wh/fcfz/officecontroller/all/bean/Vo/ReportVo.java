package wh.fcfz.officecontroller.all.bean.Vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.Report;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportVo extends Report {
    private String userName;
    private String departName;
    @ExcelIgnore
    private List<String> fileUrls;
    //分享人的基本信息存
    public ReportVo(Report report){
        super(report);
    }
}
