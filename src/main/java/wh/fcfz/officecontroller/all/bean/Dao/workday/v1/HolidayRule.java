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
// 节假日规则
public class HolidayRule {
    private String holiday;               // 节假日名称 (如 "new year's day")
    private List<WorkSchedules> workSchedules;     // 时间段规则
    private Integer dayWorkHours;         // 节假日工作时长（小时）

    // Getter 和 Setter 方法
}
