package wh.fcfz.officecontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class OfficeControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfficeControllerApplication.class, args);
    }
}