package wh.fcfz.officecontroller.all.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("file")
public class File {

    /**
     * Unique identifier for the attachment.
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * Name of the file.
     */
    private String fileName;

    /**
     * UUID of the file for unique identification.
     */
    private String fileUuid;

    /**
     * Path of the file on the disk.
     */
    private String filePath;

    /**
     * Size of the file in bytes.
     */
    private Long fileSize;

    /**
     * Type of the file (e.g., pdf).
     */
    private String fileType;

    /**
     * URL to access the file on the cloud server.
     */
    private String fileUrl;

    /**
     * Additional description or notes about the file.
     */
    private String description;

    /**
     * ID of the user who uploaded the file.
     */
    private Integer uploaderId;

    /**
     * Timestamp when the file was uploaded.
     */
    private Timestamp uploadTime;

    /**
     * Type of business the file is associated with (e.g., reimbursement, travel).
     */
    private String businessType;

    /**
     * ID of the business record associated with the file.
     */
    private Integer businessId;

    /**
     * ID of the user who owns the file.
     */
    private Integer fileOwnerId;

    /**
     * Status of the file (0 - deleted, 1 - normal, 2 - archived).
     */
    private Byte fileStatus;
}
