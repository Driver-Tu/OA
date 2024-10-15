package wh.fcfz.officecontroller.config.ueditor;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class UEditorConfig {
    //执行上传图片的action名称 默认值：uploadimage 必填
    private String imageActionName = "image";

    // 提交的图片表单名称，默认值：upfile
    private String imageFieldName = "file";

    // 上传大小限制，单位B，默认值：2048000
    private Integer imageMaxSize = 10485760;

    // 允许上传的图片格式
    private String[] imageAllowFiles = {".jpg", ".png", ".jpeg"};

    // 图片访问路径前缀，默认值：空 必填
    private String imageUrlPrefix = "http://localhost:8088/";
}
