package wh.fcfz.officecontroller.all.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;


public interface DepartService extends IService<Depart> {

    Result<Page<Depart>> selectPageAll(Depart depart, Integer pageNum, Integer pageSize);

    Result<Depart> selectById(Integer id);

    Result<Depart> saveDepart(Depart depart);

    Result<Depart> updateDept(Depart depart);

    Result<String> deleteDeptsBatch(List<Integer> ids);

}
