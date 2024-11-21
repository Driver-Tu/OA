package wh.fcfz.officecontroller.all.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wh.fcfz.officecontroller.all.bean.Dao.NotificationPush;
import wh.fcfz.officecontroller.all.bean.Dao.User;
import wh.fcfz.officecontroller.all.bean.Dto.UserDto;
import wh.fcfz.officecontroller.all.bean.Vo.UserVo;
import wh.fcfz.officecontroller.all.mapper.NotificationPushMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.tool.MyException;
import wh.fcfz.officecontroller.all.tool.Result;
import wh.fcfz.officecontroller.all.tool.mail.MailUtil;
import wh.fcfz.officecontroller.all.tool.mail.javabean.Mail;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NotificationPushMapper notificationPushMapper;

    /**
     * 查询自己收到的邮件
     * @return
     */
    @GetMapping("/getNotice")
    public List<NotificationPush> getNotice(){
        return notificationPushMapper.selectNotifications(StpUtil.getLoginIdAsInt());
    }

    @Autowired
    private MailUtil mailUtil;

    /**
     * 保存邮件
     * @param notificationPush
     * @return
     */
    @SaCheckPermission(value={"boss","admin"}, mode= SaMode.OR)
    @PostMapping("/saveNotice")
    public Result saveNotice(@RequestBody NotificationPush notificationPush){
        String[] split = notificationPush.getRecipientIds().split(",");
        StringBuilder sb=new StringBuilder();
        Arrays.stream(split).toList().forEach(s->{
            sb.append("[").append(s).append("],");
        });
        notificationPush.setRecipientIds(sb.toString());
        notificationPush.setCreateBy(StpUtil.getLoginIdAsLong());
        notificationPush.setSendTime(new Date());
        int insert = notificationPushMapper.insert(notificationPush);
        if(insert>0){
            return new Result("200", "发送成功", notificationPush.getId());
        }else {
            throw new MyException("500","发送失败");
        }
    }
    @Autowired
    private UserMapper userMapper;

    /**
     * 发送邮件
     * @param id
     * @return
     */
    @SaCheckPermission(value={"admin","boss"}, mode= SaMode.OR)
    @PostMapping("/sendEmail")
    private boolean sendMail(@RequestParam Long id){
        NotificationPush notificationPush=notificationPushMapper.selectById(id);
        if(notificationPush.getRecipientIds().contains("[")){
            String[] split = notificationPush.getRecipientIds().replaceAll("\\[|\\]", "").split(",");
            for (int i = 0; i < split.length; i++) {
                User user = userMapper.selectById(Integer.parseInt(split[i]));
                senEmail(user.getEmail(),notificationPush);
            }
        }else {
            UserDto userDto = new UserDto();
            if (notificationPush.getRecipientIds().equals("全体员工")) {
                userDto.setDepartName(null);
            }else {
                userDto.setDepartName(notificationPush.getRecipientIds());
            }
            List<UserVo> userVos = userMapper.selectUserList(userDto);
            userVos.stream().parallel().forEach(userVo -> {
                log.info("发送给: "+userVo.getEmail(),notificationPush.toString());
                senEmail(userVo.getEmail(),notificationPush);
            });
        }
        notificationPush.setStatus("已发送");
        boolean b = notificationPushMapper.updateById(notificationPush) > 0;
        if(!b){
            throw new MyException("500","发送失败");
        }
        return true;
    }

    /**
     * 发送QQ邮箱
     * @param email
     * @param notificationPush
     */
    private void senEmail(String email,NotificationPush notificationPush) {
        /**
         * 判断邮箱格式是否符合qq
         */
        if(!email.contains("qq.com")){
           return;
        }
        Mail mail=new Mail();
        mail.setRecipient(email);
        if(notificationPush.getRecipientIds().contains("[")){
            mail.setSubject(notificationPush.getNotificationType()+" | 致"+ email);
        }else {
            mail.setSubject(notificationPush.getNotificationType()+" | 致"+ notificationPush.getRecipientIds());
        }
        mail.setContent("标题: "+ notificationPush.getTitle()+"\n 通知内容: \n"+ notificationPush.getContent());
        mailUtil.sendSimpleMail(mail);
    }
}
