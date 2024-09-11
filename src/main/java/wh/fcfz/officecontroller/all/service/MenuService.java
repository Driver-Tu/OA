package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Menu;
import wh.fcfz.officecontroller.all.tool.Result;

public interface MenuService extends IService<Menu> {
    Result<Menu> selectById();
}
