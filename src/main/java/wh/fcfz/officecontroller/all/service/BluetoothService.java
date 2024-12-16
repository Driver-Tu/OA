package wh.fcfz.officecontroller.all.service;

import wh.fcfz.officecontroller.all.bean.Dao.Bluetooth;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【bluetooth】的数据库操作Service
* @createDate 2024-12-10 10:26:29
*/
public interface BluetoothService extends IService<Bluetooth> {
    /**
     * 查询蓝牙信息
     */
    List<Bluetooth> selectBluetooth();

}
