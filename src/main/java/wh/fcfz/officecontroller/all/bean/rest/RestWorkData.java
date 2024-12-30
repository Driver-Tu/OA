package wh.fcfz.officecontroller.all.bean.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestWorkData {
    private Date date;
    private Boolean work;
}
