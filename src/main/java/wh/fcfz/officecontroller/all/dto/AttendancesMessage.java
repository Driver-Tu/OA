package wh.fcfz.officecontroller.all.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendancesMessage {
    private Integer attendanceId;
    private String attendanceUserName;
    private String attendanceUserDepartName;
    private java.sql.Timestamp timeIn;
    private java.sql.Timestamp timeOut;
    private String date;
    private String status;
    private String address;
    private String type;
}
