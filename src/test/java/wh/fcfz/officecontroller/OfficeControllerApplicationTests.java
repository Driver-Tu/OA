package wh.fcfz.officecontroller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.service.ReportService;
import wh.fcfz.officecontroller.all.tool.SpringUtils;

@SpringBootTest
class OfficeControllerApplicationTests {
    @Test
    @Transactional
    void contextLoads() {
        ReportService reportService = SpringUtils.getBean(ReportService.class);
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = reportService.removeById(893);
        System.out.println(b);
        if(b){

        }else {
            throw new RuntimeException("删除失败");
        }
        try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b1 = reportService.removeById(894);
        System.out.println(b1);
        if(b1){

        }else {
            throw new RuntimeException("删除失败");
        }
          try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b2 = reportService.removeById(890);
          System.out.println(b2);
        if(b2){

            }else {
                throw new RuntimeException("删除失败");
            }

//        CompletableFuture<Boolean> result1 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            boolean b = reportService.removeById(890);
//            System.out.println(b);
//            if(b){
//                return true;
//            }else {
//                throw new RuntimeException("删除失败");
//            }
//        });
//
//        CompletableFuture<Boolean> result2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            boolean b1 = reportService.removeById(891);
//            System.out.println(b1);
//            if(b1){
//                return true;
//            }else {
//                throw new RuntimeException("删除失败");
//            }
//        });
//
//        CompletableFuture<Boolean> result3 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            boolean b2 = reportService.removeById(2000);
//            System.out.println(b2);
//            if(b2){
//                return true;
//            }else {
//                throw new RuntimeException("删除失败");
//            }
//        });
//        CompletableFuture.allOf(result1, result2, result3).join();
    }

}
