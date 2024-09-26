package wh.fcfz.officecontroller.all.tool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

import static wh.fcfz.officecontroller.config.file.SystemConfig.DEFAULT_PAGE_NUM;
import static wh.fcfz.officecontroller.config.file.SystemConfig.DEFAULT_PAGE_SIZE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage<T> {
    private Integer pageNum=DEFAULT_PAGE_NUM;
    private Integer pageSize=DEFAULT_PAGE_SIZE;
    private T data;
    private Integer total;
   private  String departmentName;
   private String userName;
   private Map<String,Object> params;
}
