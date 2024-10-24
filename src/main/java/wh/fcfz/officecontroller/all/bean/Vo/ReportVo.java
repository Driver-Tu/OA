package wh.fcfz.officecontroller.all.bean.Vo;

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
    private List<String> fileUrls;
    //分享人的基本信息存
    public ReportVo(Report report){
        super(report);
    }
}
