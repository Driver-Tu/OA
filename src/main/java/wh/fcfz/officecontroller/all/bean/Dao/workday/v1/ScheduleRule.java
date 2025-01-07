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
// 年规则
public class ScheduleRule {
    private String year;                  // 年份 (如 "2025" 或 "0" 表示任意年份)
    private List<MonthRule> months;       // 月规则
    private List<WeekRule> weeks;         // 周规则
    private List<DayRule> days;           // 日规则
    private List<WorkSchedules> workSchedules;     // 时间段规则
    private Integer dayWorkHours;         // 每日工作时长（小时）
    private List<HolidayRule> holidays;   // 节假日规则

    // Getter 和 Setter 方法
}
