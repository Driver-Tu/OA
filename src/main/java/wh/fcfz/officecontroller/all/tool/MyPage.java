package wh.fcfz.officecontroller.all.tool;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static wh.fcfz.officecontroller.config.SystemConfig.DEFAULT_PAGE_NUM;
import static wh.fcfz.officecontroller.config.SystemConfig.DEFAULT_PAGE_SIZE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage<T> {
    private Integer pageNum=DEFAULT_PAGE_NUM;
    private Integer pageSize=DEFAULT_PAGE_SIZE;
    private T data;
    private Integer total;

}
