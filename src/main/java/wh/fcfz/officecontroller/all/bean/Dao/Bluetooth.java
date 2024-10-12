package wh.fcfz.officecontroller.all.bean.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("bluetooth")
public class Bluetooth {
    @TableId(type = IdType.AUTO)
    private Integer bluetoothId; // 蓝牙ID

    private String deviceId; // 蓝牙物理地址

    private String bluetoothName; // 蓝牙名称

    private String advertisServiceUuids; // 蓝牙广播服务的 UUIDs

    private Timestamp ctTime; // 创建时间

    private Timestamp upTime; // 更新时间

    private Integer status; // 状态 (1 为启用，0 为关闭)
}
