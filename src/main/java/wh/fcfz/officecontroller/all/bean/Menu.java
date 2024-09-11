package wh.fcfz.officecontroller.all.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("menu")
public class Menu {
  private Integer menuId;
  private String menuName;
  private Integer permission;
}
