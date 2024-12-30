package wh.fcfz.officecontroller.all.bean.Vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchedulingVo {
    /**
     * 排班规则id
     */
    private Integer schedulingId;
    /**
     * 用户id
     */
    @TableId
    private Integer userId;
    /**
     * 规则
     */
    private String rule;
}
