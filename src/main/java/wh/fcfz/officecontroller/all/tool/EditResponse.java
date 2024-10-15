package wh.fcfz.officecontroller.all.tool;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditResponse {

    //状态值 成功只能返回SUCCESS(必须大写),否则填写失败原因
    private String state;

    //上传路径
    private String url; //要想在编辑器中正确展示图片,请确保 imageUrlPrefix + url路径可以访问到图片资源

    //标题,可不填
    private String title;

    //上传文件原始名称 绑定在img标签的alt属性中
    private String original;
}
