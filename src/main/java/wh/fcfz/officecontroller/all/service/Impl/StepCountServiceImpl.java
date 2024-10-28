package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Dao.StepCount;
import wh.fcfz.officecontroller.all.mapper.StepCountMapper;
import wh.fcfz.officecontroller.all.service.StepCountService;

@Service
public class StepCountServiceImpl extends ServiceImpl<StepCountMapper, StepCount> implements StepCountService {
    @Override
    public StepCount selectByType(String type) {
        return this.getOne(new LambdaQueryWrapper<StepCount>().eq(StepCount::getStepType, type));
    }
}
