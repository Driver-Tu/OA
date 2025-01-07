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
// 月规则
public class MonthRule {
    private Integer month;                // 月份 (1-12)
    private List<WeekRule> weeks;         // 周规则
    private List<DayRule> days;           // 日规则
    private List<WorkSchedules> workSchedules;     // 时间段规则
    private Integer dayWorkHours;         // 每日工作时长（小时）

    // Getter 和 Setter 方法
}
