package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import wh.fcfz.officecontroller.all.service.DepartService;
import wh.fcfz.officecontroller.all.mapper.DepartMapper;

import java.sql.Wrapper;
import java.util.List;

@Slf4j
@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements DepartService {

    @Autowired
    private DepartMapper departMapper;

    @Override
    public Result<Depart> selectPageAll(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            log.error("分页参数为空");
            return new Result<>(ResponseEnum.PARAM_ERROR, null);
        }

        Page<Depart> page = new Page<>(pageNum, pageSize);
        Page<Depart> departmentPage = departMapper.selectPage(page, null);

        if (departmentPage.getRecords().isEmpty()) {
            log.info("分页查询部门列表结果为空");
        }

        return new Result(ResponseEnum.SUCCESS, departmentPage);
    }


    @Override
    public Result<Depart> selectByID(Integer id) {
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

    @Override
    public Result<Depart> selectByName(String deptName) {

        if(deptName == null) {
            log.error("查询部门名称为空");
            return new Result<>(ResponseEnum.DEPT_NAME_NULL, null);
        }
        QueryWrapper<Depart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("depart_name", deptName);
        List<Depart> depart = departMapper.selectList(queryWrapper);
        if(depart == null || depart.isEmpty()) {
            log.error("未找到部门");
            return new Result(ResponseEnum.DEPT_NOT_EXIST, depart);
        }

        return new Result(ResponseEnum.SUCCESS, depart);
    }
}
