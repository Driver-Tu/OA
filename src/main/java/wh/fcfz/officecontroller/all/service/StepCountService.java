package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Dao.StepCount;

public interface StepCountService extends IService<StepCount> {
    //查询对应的一天记录
    StepCount selectByType(String type);
}
