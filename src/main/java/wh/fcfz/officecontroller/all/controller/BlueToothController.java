package wh.fcfz.officecontroller.all.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wh.fcfz.officecontroller.all.bean.Dao.Bluetooth;
import wh.fcfz.officecontroller.all.service.BluetoothService;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bluetooth")
@Tag(name = "蓝牙控制器")
public class BlueToothController {
    private final BluetoothService bluetoothService;

    public BlueToothController(BluetoothService bluetoothService) {
        this.bluetoothService = bluetoothService;
    }

    /**
     * 查询
     */
    @GetMapping("/getBluetooth")
    public Result<List<Bluetooth>> selectBluetooth(){
        return new Result<>(ResponseEnum.SUCCESS,bluetoothService.selectBluetooth());
    }
    /**
     * 新增
     */
    @PostMapping("/addBluetooth")
    public Result<Boolean> addBluetooth(Bluetooth bluetooth){
        return new Result<>(ResponseEnum.SUCCESS,bluetoothService.save(bluetooth));
    }
    /**
     * 修改
     */
    @PostMapping("/updateBluetooth")
    public Result<Boolean> updateBluetooth(Bluetooth bluetooth){
        return new Result<>(ResponseEnum.SUCCESS,bluetoothService.updateById(bluetooth));
    }
    /**
     * 删除
     */
    @PostMapping("/deleteBluetooth")
    public Result<Boolean> deleteBluetooth(Integer bluetoothId){
        return new Result<>(ResponseEnum.SUCCESS,bluetoothService.removeById(bluetoothId));
    }
}
