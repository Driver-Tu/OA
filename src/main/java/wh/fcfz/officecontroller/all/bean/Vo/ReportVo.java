package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.Report;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportVo extends Report {
    private String userName;
    private String departName;
    public ReportVo(Report report){
        super(report);
    }
}
