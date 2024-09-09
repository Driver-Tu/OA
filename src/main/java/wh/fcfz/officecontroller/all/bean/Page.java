package wh.fcfz.officecontroller.all.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private Integer pageNum;
    private Integer pageSize;
    private T data;
}
