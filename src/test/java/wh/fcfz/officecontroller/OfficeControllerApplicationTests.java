package wh.fcfz.officecontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wh.fcfz.officecontroller.all.bean.Dao.NotificationPush;
import wh.fcfz.officecontroller.all.tool.mail.MailUtil;
import wh.fcfz.officecontroller.all.tool.mail.javabean.Mail;

import java.util.Date;

@SpringBootTest
class OfficeControllerApplicationTests {

    @Autowired
    private MailUtil mailUtil;

    @Test
    void contextLoads() {
        NotificationPush notificationPush = new NotificationPush(1L, "公告", "放假", "明天放假，记得关电脑", "已发送", 7L, new Date(), new Date(), new Date(),"all",null);
        Mail mail=new Mail();
        mail.setRecipient("3109702282@qq.com");
        mail.setSubject(notificationPush.getNotificationType());
        mail.setContent(notificationPush.getTitle()+"\n 通知类容: \n"+notificationPush.getContent());
        mailUtil.sendSimpleMail(mail);
    }

}
