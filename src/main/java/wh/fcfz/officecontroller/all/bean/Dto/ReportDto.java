package wh.fcfz.officecontroller.all.bean.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private Integer reportId;
    private String reportName;
    private String type;
    private java.sql.Date reportDate;
    private List<String> filePath;
    private String content;
    private String userName;
    private String departName;
}