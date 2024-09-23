package wh.fcfz.officecontroller.all.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("address")
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer addressId; // 地址ID

    private String addressName; // 地址名称

    private BigDecimal longitude; // 经度

    private BigDecimal latitude; // 纬度

    private Timestamp ctTime; // 创建时间

    private Timestamp upTime; // 更新时间

    private Integer status; // 状态 (1 代表启用，0 代表关闭)
}
