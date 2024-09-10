package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import wh.fcfz.officecontroller.all.service.DepartService;
import wh.fcfz.officecontroller.all.mapper.DepartMapper;

import java.sql.Wrapper;


@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements DepartService {

    @Autowired
    private DepartMapper departMapper;

    @Override
    public Result<Depart> SelectPageALL(Integer pageNum, Integer pageSize) {
        Page<Depart> page = new Page<>(pageNum, pageSize);

        Page<Depart> deptPageList = departMapper.selectPage(page, null);

        if(deptPageList == null) {
            log.debug("分页查询部门为空");
        }

        // 查询分页结果
        return new Result(ResponseEnum.SUCCESS, deptPageList); // null 表示无查询条件
    }

    @Override
    public Result<Depart> SelectByID(Integer id) {
        if(id == null) {
            log.error("查询 id 为空");
            return new Result<>(ResponseEnum.DEPT_ID_NULL, null);
        }
        Depart depart = departMapper.selectById(id);
        if(depart == null) {
            log.error("未找到部门");
            return new Result(ResponseEnum.DEPT_NOT_EXIST, depart);
        }
        return new Result(ResponseEnum.SUCCESS, depart);
    }

}
