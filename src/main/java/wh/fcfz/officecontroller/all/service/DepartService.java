package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.tool.Result;


public interface DepartService extends IService<Depart> {

    Result<Depart> selectPageAll(Depart depart, Integer pageNum, Integer pageSize);

    Result<Depart> selectById(Integer id);

    Result<Depart> saveDepart(Depart depart);

    Result<Depart> deleteById(Integer id);

    Result<Depart> updateDept(Depart depart);

//    Result<Depart> selectByName(String deptName);
}
