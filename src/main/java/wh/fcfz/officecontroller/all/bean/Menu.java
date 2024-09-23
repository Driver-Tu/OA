package wh.fcfz.officecontroller.all.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("menu")
public class Menu {
  @TableId(type = IdType.AUTO)
  private Integer menuId; // 主菜单ID

  private String menuName; // 名称

  private Integer permission; // 职权

  private String menuRouter; // 路由

  private Integer type; // 层级（几层）

  private Integer fatherMenuId; // 父菜单ID

  private Timestamp ctTime; // 创建时间

  private Timestamp upTime; // 更新时间
}
