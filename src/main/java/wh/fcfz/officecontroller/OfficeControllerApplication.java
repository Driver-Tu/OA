package wh.fcfz.officecontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class OfficeControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OfficeControllerApplication.class, args);
    }
}