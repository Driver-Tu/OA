package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Dao.Menu;
import wh.fcfz.officecontroller.all.bean.Vo.MenuVo;
import wh.fcfz.officecontroller.all.mapper.MenuMapper;
import wh.fcfz.officecontroller.all.service.MenuService;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public Result<List<MenuVo>> getMenuTree() {
        // 从数据库查询符合当前用户权限的所有菜单，保存到 dbmenuList
        List<Menu> dbmenuList = menuMapper.selectByPermissionMenu(Integer.parseInt(StpUtil.getRoleList().get(0)));

        // 构建一个父菜单映射
        Map<Integer, List<Menu>> parentMenuMap = dbmenuList.stream()
                .collect(Collectors.groupingBy(Menu::getFatherMenuId)); // 按 parentId 分组

        // 获取所有父菜单（parent_id 为 0 的菜单）
        List<MenuVo> topLevelMenus = dbmenuList.stream()
                .filter(menu -> menu.getFatherMenuId() == 0)
                .map(menu -> convertToMenuMessage(menu, parentMenuMap))
                .collect(Collectors.toList());

        return new Result<>(ResponseEnum.SUCCESS, topLevelMenus);
    }

    // 将 Menu 转换为 MenuMessage 并递归构建树形结构
    private MenuVo convertToMenuMessage(Menu menu, Map<Integer, List<Menu>> parentMenuMap) {
        // 获取当前菜单的子菜单列表
        List<MenuVo> sonMenus = Optional.ofNullable(parentMenuMap.get(menu.getMenuId()))
                .orElse(Collections.emptyList())  // 如果没有子菜单则返回空列表
                .stream()
                .map(subMenu -> convertToMenuMessage(subMenu, parentMenuMap))  // 递归调用转换子菜单
                .collect(Collectors.toList());

        // 构建 MenuMessage 并将子菜单列表赋值给 sonMenus
        return new MenuVo(menu.getMenuId(), menu.getMenuName(),menu.getMenuRouter(),sonMenus);
    }
}
