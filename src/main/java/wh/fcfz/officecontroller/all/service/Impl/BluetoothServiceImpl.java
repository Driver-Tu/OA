package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import wh.fcfz.officecontroller.all.bean.Dao.Bluetooth;
import wh.fcfz.officecontroller.all.service.BluetoothService;
import wh.fcfz.officecontroller.all.mapper.BluetoothMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【bluetooth】的数据库操作Service实现
* @createDate 2024-12-10 10:26:29
*/
@Service
public class BluetoothServiceImpl extends ServiceImpl<BluetoothMapper, Bluetooth>
    implements BluetoothService{

    private final BluetoothMapper bluetoothMapper;

    public BluetoothServiceImpl(BluetoothMapper bluetoothMapper) {
        this.bluetoothMapper = bluetoothMapper;
    }

    @Override
    public List<Bluetooth> selectBluetooth() {
        return bluetoothMapper.selectList(new LambdaQueryWrapper<>());
    }
}




