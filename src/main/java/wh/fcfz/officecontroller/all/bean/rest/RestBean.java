package wh.fcfz.officecontroller.all.bean.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestBean {
    private Integer code;
    private String msg;
    private String data;
}
