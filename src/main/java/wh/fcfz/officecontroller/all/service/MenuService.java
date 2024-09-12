package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Menu;
import wh.fcfz.officecontroller.all.dto.MenuMessage;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

public interface MenuService extends IService<Menu> {
    Result<List<MenuMessage>> getMenuTree();
}
