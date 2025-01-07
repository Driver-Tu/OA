package wh.fcfz.officecontroller.all.bean.Dao.workday.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
// 日规则
public class DayRule {
    private String day;                   // 天标识 ("1" 表示周一，"1-5" 表示周一到周五)
    private List<WorkSchedules> workSchedules;     // 时间段规则
    private Integer dayWorkHours;         // 当天工作时长（小时）

    // Getter 和 Setter 方法
}
