package wh.fcfz.officecontroller.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.service.Impl.MenuControllerImpl;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuControllerImpl menuService;


}
