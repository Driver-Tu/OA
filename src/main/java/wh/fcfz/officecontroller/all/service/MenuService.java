package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.Menu;
import wh.fcfz.officecontroller.all.bean.Vo.MenuVo;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

public interface MenuService extends IService<Menu> {
    Result<List<MenuVo>> getMenuTree();
}
