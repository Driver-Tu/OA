package wh.fcfz.officecontroller.all.bean.Dao.workday.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
// 时间段规则
public class WorkSchedules {
    private String startTime;             // 开始时间 (如 "09:00:00")
    private String endTime;               // 结束时间 (如 "12:00:00")

    // Getter 和 Setter 方法
}
