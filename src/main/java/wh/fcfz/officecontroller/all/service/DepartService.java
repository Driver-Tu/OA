package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.bean.Result;
import wh.fcfz.officecontroller.all.bean.User;

public interface DepartService extends IService<Depart> {

    Result<Depart> SelectPageALL(Integer pageNum, Integer pageSize);

}
