package wh.fcfz.officecontroller.all.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wh.fcfz.officecontroller.all.bean.SonMenu;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuMessage {
    private Integer menuId;
    private String menuName;
    private Integer permission;
    private List<SonMenu> sonMenus;
}
