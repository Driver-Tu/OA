package wh.fcfz.officecontroller.all.bean.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.Dao.ApprovalForms;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalFormsVo extends ApprovalForms {
    private String UserName;
    private String departmentName;
    private Map<String,Object> map;
    private List<String> fileList;
    public  ApprovalFormsVo(ApprovalForms ApprovalForms) {
        super(ApprovalForms);
    }
}
