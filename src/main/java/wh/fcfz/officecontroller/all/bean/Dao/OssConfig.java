package wh.fcfz.officecontroller.all.bean.Dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName oss_config
 */
@TableName(value ="oss_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssConfig implements Serializable {
    /**
     * oss配置id
     */
    @TableId
    private Integer ossConfigId;

    /**
     * 服务节点名称
     */
    private String ossEndpoint;

    /**
     * 账户id
     */
    private String accessKeyId;

    /**
     * 账户秘钥
     */
    private String accessKeySecret;

    /**
     * 服务称呼
     */
    private String bucketName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OssConfig other = (OssConfig) that;
        return (this.getOssConfigId() == null ? other.getOssConfigId() == null : this.getOssConfigId().equals(other.getOssConfigId()))
            && (this.getOssEndpoint() == null ? other.getOssEndpoint() == null : this.getOssEndpoint().equals(other.getOssEndpoint()))
            && (this.getAccessKeyId() == null ? other.getAccessKeyId() == null : this.getAccessKeyId().equals(other.getAccessKeyId()))
            && (this.getAccessKeySecret() == null ? other.getAccessKeySecret() == null : this.getAccessKeySecret().equals(other.getAccessKeySecret()))
            && (this.getBucketName() == null ? other.getBucketName() == null : this.getBucketName().equals(other.getBucketName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOssConfigId() == null) ? 0 : getOssConfigId().hashCode());
        result = prime * result + ((getOssEndpoint() == null) ? 0 : getOssEndpoint().hashCode());
        result = prime * result + ((getAccessKeyId() == null) ? 0 : getAccessKeyId().hashCode());
        result = prime * result + ((getAccessKeySecret() == null) ? 0 : getAccessKeySecret().hashCode());
        result = prime * result + ((getBucketName() == null) ? 0 : getBucketName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ossConfigId=").append(ossConfigId);
        sb.append(", ossEndpoint=").append(ossEndpoint);
        sb.append(", accessKeyId=").append(accessKeyId);
        sb.append(", accessKeySecret=").append(accessKeySecret);
        sb.append(", bucketName=").append(bucketName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}