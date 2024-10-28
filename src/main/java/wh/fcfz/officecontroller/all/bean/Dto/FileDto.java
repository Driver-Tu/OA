package wh.fcfz.officecontroller.all.bean.Dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private Integer id;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private Long fileSize;
    private String fileType;
    private String fileUrl;
    private String description;
    private Integer uploaderId;
    private Timestamp uploadTime;
    private String businessType;
    private Integer businessId;
    private Integer fileOwnerId;
    private Byte fileStatus;
}
