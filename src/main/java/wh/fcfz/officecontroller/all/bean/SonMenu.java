package wh.fcfz.officecontroller.all.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("son_menu")
public class SonMenu {
  @TableId(type = IdType.AUTO)
  private Integer sonMenuId;
  private String sonMenuName;
  private String sonMenuRouter;
  private Integer sonMenuFather;
  private Integer sonMenuPermission;


}
