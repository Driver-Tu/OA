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
     * 附件的唯一标识符。
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名称。
     */
    private String fileName;

    /**
     * 文件的 UUID，用于唯一标识。
     */
    private String fileUuid;

    /**
     * 文件在磁盘上的路径。
     */
    private String filePath;

    /**
     * 文件大小（以字节为单位）。
     */
    private Long fileSize;

    /**
     * 文件类型（例如 pdf）。
     */
    private String fileType;

    /**
     * 在云服务器上访问文件的 URL。
     */
    private String fileUrl;

    /**
     * 关于文件的附加描述或备注。
     */
    private String description;

    /**
     * 上传文件的用户 ID。
     */
    private Integer uploaderId;

    /**
     * 文件上传的时间戳。
     */
    private Timestamp uploadTime;

    /**
     * 文件关联的业务类型（例如报销、出差）。
     */
    private String businessType;

    /**
     * 关联的业务记录 ID。
     */
    private Integer businessId;

    /**
     * 文件拥有者的 ID。
     */
    private Integer fileOwnerId;

    /**
     * 文件的状态（0 - 已删除，1 - 正常，2 - 已归档）。
     */
    private Byte fileStatus;
}
