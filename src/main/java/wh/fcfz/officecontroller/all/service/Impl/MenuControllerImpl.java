package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Menu;
import wh.fcfz.officecontroller.all.mapper.MenuMapper;
import wh.fcfz.officecontroller.all.service.MenuService;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

@Service
public class MenuControllerImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public Result<Menu> selectById() {
        if (!StpUtil.isLogin(StpUtil.getLoginId())){
            return new Result<Menu>(ResponseEnum.USER_NOT_LOGIN,null);
        }
        Integer permission = menuMapper.selectByPermission((Integer) StpUtil.getLoginId());
        if (permission == 3){
            return new Result<Menu>(ResponseEnum.USER_NOT_PERMISSION,null);
        }

        return null;
    }
}
