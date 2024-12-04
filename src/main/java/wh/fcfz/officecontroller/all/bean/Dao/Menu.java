package wh.fcfz.officecontroller.all.bean.Dao;

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

  /**
   * 菜单的唯一标识符。
   */
  @TableId(type = IdType.AUTO)
  private Integer menuId;

  /**
   * 路由主题。
   */
  private String menuLabel;

  /**
   * 菜单名称。
   */
  private String menuName;

  /**
   * 父菜单ID。
   */
  private Integer parentId;

  /**
   * 菜单的显示顺序。
   */
  private Integer menuSort;

  /**
   * 菜单的路由地址。
   */
  private String path;

  /**
   * 组件路径。
   */
  private String component;

  /**
   * 路由参数。
   */
  private String queryParam;

  /**
   * 是否为外链（0是 1否）。
   */
  private Integer isFrame;

  /**
   * 是否缓存（0缓存 1不缓存）。
   */
  private Integer isCache;

  /**
   * 菜单类型（D目录 M菜单 B按钮）。
   */
  private String menuType;

  /**
   * 显示状态（0显示 1隐藏）。
   */
  private String visible;

  /**
   * 菜单状态（0正常 1停用）。
   */
  private String status;

  /**
   * 权限 ID。
   */
  private Integer permsId;

  /**
   * 菜单图标。
   */
  private String icon;

  /**
   * 创建部门。
   */
  private Integer createDept;

  /**
   * 创建者 ID。
   */
  private Integer createBy;

  /**
   * 创建时间。
   */
  private Timestamp createTime;

  /**
   * 更新者 ID。
   */
  private Integer updateBy;

  /**
   * 更新时间。
   */
  private Timestamp updateTime;

  /**
   * 备注。
   */
  private String remark;
}
