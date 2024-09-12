package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Menu;
import wh.fcfz.officecontroller.all.bean.SonMenu;
import wh.fcfz.officecontroller.all.dto.MenuMessage;
import wh.fcfz.officecontroller.all.mapper.MenuMapper;
import wh.fcfz.officecontroller.all.service.MenuService;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public Result<List<MenuMessage>> selectById() {
        if (!StpUtil.isLogin(StpUtil.getLoginId())){
            return new Result<>(ResponseEnum.USER_NOT_LOGIN,null);
        }
        Integer permission = menuMapper.selectByPermission(Integer.parseInt(StpUtil.getLoginId().toString()));
       List<Menu>  menus = menuMapper.selectByPermissionMenu(permission);
       List<MenuMessage> menuMessages = new ArrayList<>();
        menus.forEach(menu -> {
            List<SonMenu> sonMenus = menuMapper.selectAllSonMenu(menu.getPermission(), menu.getMenuId());
            MenuMessage menuMessage = new MenuMessage(menu.getMenuId(), menu.getMenuName(), menu.getPermission(), sonMenus);
            menuMessages.add(menuMessage);
        });
        return new Result<>(ResponseEnum.SUCCESS, menuMessages);
    }
}
