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
// 周规则
public class WeekRule {
    private String week;                  // 周标识 ("1" 表示第一周，"every" 表示每周)
    private List<DayRule> days;           // 日规则
    private List<WorkSchedules> workSchedules;     // 时间段规则
    private Integer dayWorkHours;         // 每日工作时长（小时）

    // Getter 和 Setter 方法
}

