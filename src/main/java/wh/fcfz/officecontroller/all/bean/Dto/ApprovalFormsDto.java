package wh.fcfz.officecontroller.all.bean.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalFormsDto {
    private Long formId;
    private Integer applicantId;
    private Long allId;
    private String fromName;
    private java.sql.Timestamp applicationDate;
    private String status;
    private String type;
    private String description;
    private String UserName;
    private String departmentName;
}
