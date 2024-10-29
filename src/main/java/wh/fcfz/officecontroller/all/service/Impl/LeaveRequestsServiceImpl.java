package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Dao.review.LeaveRequests;
import wh.fcfz.officecontroller.all.mapper.LeaveRequestsMapper;
import wh.fcfz.officecontroller.all.service.LeaveRequestsService;

@Service
public class LeaveRequestsServiceImpl extends ServiceImpl<LeaveRequestsMapper, LeaveRequests> implements LeaveRequestsService {
}
