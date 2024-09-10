package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.tool.Result;


public interface DepartService extends IService<Depart> {

    Result<Depart> selectPageAll(Integer pageNum, Integer pageSize);

    Result<Depart> selectByID(Integer id);

    Result<Depart> selectByName(String deptName);
}
