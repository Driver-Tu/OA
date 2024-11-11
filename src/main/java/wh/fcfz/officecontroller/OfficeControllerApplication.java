package wh.fcfz.officecontroller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@EnableTransactionManagement
public class OfficeControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfficeControllerApplication.class, args);
    }
}