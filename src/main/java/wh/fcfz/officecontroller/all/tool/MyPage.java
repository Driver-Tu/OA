package wh.fcfz.officecontroller.all.tool;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private T data;
    private Integer total;
}
