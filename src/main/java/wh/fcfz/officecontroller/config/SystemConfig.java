package wh.fcfz.officecontroller.config;


import java.io.File;
import java.net.URISyntaxException;

public class SystemConfig {

    // 文件上传根目录（静态属性）
    public static final String FILE_UPLOAD_ROOT_DIR="D:\\My Project\\myOASys\\src\\main\\resources\\file\\";

    // 分页默认配置
    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 5;
    public static final int MAX_PAGE_SIZE = 50;

    // 初始化文件位置
//    static {
//        try {
//            // 获取当前类的类加载器
//            ClassLoader classLoader = SystemConfig.class.getClassLoader();
//
//            // 获取资源文件夹的路径
//            File file = new File(classLoader.getResource("file").toURI());
//
//            // 设置上传根目录为文件夹的绝对路径
//            FILE_UPLOAD_ROOT_DIR = file.getAbsolutePath() + File.separator;
//
//            // 检查文件夹是否存在，如果不存在则创建它
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//        } catch (URISyntaxException | NullPointerException e) {
//            throw new RuntimeException("Failed to initialize file upload root directory", e);
//        }
//    }

    // 私有构造方法，防止实例化
    private SystemConfig() {
        // This class should not be instantiated
    }
}