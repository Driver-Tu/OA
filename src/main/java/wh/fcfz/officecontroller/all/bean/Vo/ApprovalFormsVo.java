package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalFormsVo{
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
    private Map<String,Object> map;
    private List<String> fileList;
}
