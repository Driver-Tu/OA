package wh.fcfz.officecontroller.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wh.fcfz.officecontroller.all.bean.Dao.NotificationPush;

import java.util.List;

/**
* @author zds
* @description 针对表【notification_push(通知推送表)】的数据库操作Mapper
* @createDate 2024-11-14 14:05:47
* @Entity generator.domain.NotificationPush
*/
@Mapper
public interface NotificationPushMapper extends BaseMapper<NotificationPush> {
    List<NotificationPush> selectNotifications(Integer userId);

}




