package wh.fcfz.officecontroller.all.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("menu")
public class Menu {
@TableId(type = IdType.AUTO)
  private Integer menuId;
  private String menuName;
  private Integer permission;
  private String menuRouter;
  private Integer type;
  private Integer fatherMenuId;


  public Integer getMenuId() {
    return menuId;
  }

  public void setMenuId(Integer menuId) {
    this.menuId = menuId;
  }


  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }


  public Integer getPermission() {
    return permission;
  }

  public void setPermission(Integer permission) {
    this.permission = permission;
  }


  public String getMenuRouter() {
    return menuRouter;
  }

  public void setMenuRouter(String menuRouter) {
    this.menuRouter = menuRouter;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public Integer getFatherMenuId() {
    return fatherMenuId;
  }

  public void setFatherMenuId(Integer fatherMenuId) {
    this.fatherMenuId = fatherMenuId;
  }

}
