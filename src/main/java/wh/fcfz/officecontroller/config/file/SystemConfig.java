package wh.fcfz.officecontroller.config.file;


import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URISyntaxException;


public class SystemConfig {

    // 文件上传根目录（静态属性）
    public static final String FILE_UPLOAD_ROOT_DIR = "D:\\Projects\\WeSpace\\WeOfficeSys\\OA\\src\\main\\resources\\file";
    public static final String DECLARE_REIMBURSE_FILE_DIR = "/declare/reimburse/";

    // 分页默认配置
    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 5;
    public static final int MAX_PAGE_SIZE = 50;

    // 私有构造方法，防止实例化
    private SystemConfig() {
        // This class should not be instantiated
    }
}