package wh.fcfz.officecontroller.all.bean.Vo;

import wh.fcfz.officecontroller.all.bean.Dao.Report;

public class ReportVo extends Report {
    private String userName;
    private String departName;
    public ReportVo(Report report){
        super(report);
    }
}
