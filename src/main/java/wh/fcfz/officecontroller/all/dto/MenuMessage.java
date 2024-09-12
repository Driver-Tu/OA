package wh.fcfz.officecontroller.all.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuMessage {
    private Integer menuId;
    private String menuName;
    private String menuRouter;
    private List<MenuMessage> children;
}
