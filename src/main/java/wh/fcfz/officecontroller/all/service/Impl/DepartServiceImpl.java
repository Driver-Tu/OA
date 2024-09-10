package wh.fcfz.officecontroller.all.service.Impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.mapper.DepartMapper;
import wh.fcfz.officecontroller.all.service.DepartService;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;

@Slf4j
@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements DepartService {

    @Autowired
    private DepartMapper departMapper;

    @Override
    public Result<Depart> selectPageAll(Depart depart, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            log.error("分页参数为空");
            return new Result<>(ResponseEnum.PARAM_ERROR, null);
        }

        Page<Depart> page = new Page<>(pageNum, pageSize);
        Page<Depart> departmentPage = departMapper.selectPage(page, null);
        LambdaQueryWrapper<Depart> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(depart.getDepartName() != null, Depart::getDepartName, depart.getDepartName())
                .eq(depart.getDepartTelephone() != null, Depart::getDepartTelephone, depart.getDepartTelephone())
                .eq(depart.getDepartEmail() != null, Depart::getDepartEmail, depart.getDepartEmail())
                .eq(depart.getStatus() != null, Depart::getStatus, depart.getStatus());

        if (departmentPage.getRecords().isEmpty()) {
            log.info("分页查询部门列表结果为空");
            return new Result(ResponseEnum.DEPT_NOT_EXIST, departmentPage);
        }

        return new Result(ResponseEnum.SUCCESS, departmentPage);
    }


    @Override
    public Result<Depart> selectById(Integer id) {
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

    public Result<Depart> saveDepart(Depart depart) {
        // 提前返回，减少嵌套深度
        if (depart == null) {
            log.error("部门信息为空");
            return new Result<>(ResponseEnum.INVALID_PARAM, null);
        }

        depart.setCtTime(DateTime.now().toTimestamp());
        // 插入操作并检查结果
        try {
            if (departMapper.insert(depart) > 0) {
                return new Result<>(ResponseEnum.SUCCESS, depart);
            } else {
                log.error("部门信息保存失败，depart: {}", depart);
                return new Result<>(ResponseEnum.DEPART_SAVE_FAILED, depart); // 调整错误枚举更明确
            }
        } catch (Exception e) {
            log.error("保存部门信息时出现异常，depart: {}", depart, e);
            return new Result<>(ResponseEnum.INTERNAL_SERVER_ERROR, null);
        }
    }


    @Override
    public Result<Depart> deleteById(Integer id) {
        if (id == null) {
            log.error("删除部门信息为空");
            return new Result(ResponseEnum.DEPT_ID_NULL, null);
        }

        try {
            if (departMapper.deleteById(id) > 0) {
                return new Result(ResponseEnum.SUCCESS, id);
            } else {
                log.error("部门删除失败，depart: {}", id);
                return new Result(ResponseEnum.DEPART_DELETE_FAILED, id); // 调整错误枚举更明确
            }
        } catch (Exception e) {
            log.error("删除部门信息时出现异常，depart: {}", id, e);
            return new Result(ResponseEnum.DELETE_SERVER_FAILED, null);
        }
    }

//    @Override
//    public Result<Depart> selectByName(String deptName) {
//
//        if(deptName == null) {
//            log.error("查询部门名称为空");
//            return new Result<>(ResponseEnum.DEPT_NAME_NULL, null);
//        }
//        LambdaQueryWrapper<Depart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Depart::getDepartName, deptName);
//        List<Depart> depart = departMapper.selectList(lambdaQueryWrapper);
//        if(depart == null || depart.isEmpty()) {
//            log.error("未找到部门");
//            return new Result(ResponseEnum.DEPT_NOT_EXIST, depart);
//        }
//
//        return new Result(ResponseEnum.SUCCESS, depart);
//    }
}
