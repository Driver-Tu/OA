package wh.fcfz.officecontroller.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Vo.MenuVo;
import wh.fcfz.officecontroller.all.service.Impl.MenuServiceImpl;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping("/getMenuTree")
    public Result<List<MenuVo>> selectById(){
        return menuService.getMenuTree();
    }
}
