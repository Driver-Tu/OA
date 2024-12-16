package wh.fcfz.officecontroller.all.bean.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName bluetooth
 */
@TableName(value ="bluetooth")
@Data
public class Bluetooth implements Serializable {
    /**
     * 蓝牙id
     */
    @TableId(type = IdType.AUTO)
    private Integer bluetoothId;

    /**
     * 蓝牙设备id
     */
    private String deviceId;

    /**
     * 蓝牙名称
     */
    private String bluetoothName;

    /**
     * 状态 1打开，0打开
     */
    private String status;

    /**
     * 蓝牙信标备注
     */
    private String remark;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Bluetooth other = (Bluetooth) that;
        return (this.getBluetoothId() == null ? other.getBluetoothId() == null : this.getBluetoothId().equals(other.getBluetoothId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getBluetoothName() == null ? other.getBluetoothName() == null : this.getBluetoothName().equals(other.getBluetoothName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBluetoothId() == null) ? 0 : getBluetoothId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getBluetoothName() == null) ? 0 : getBluetoothName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bluetoothId=").append(bluetoothId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", bluetoothName=").append(bluetoothName);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}