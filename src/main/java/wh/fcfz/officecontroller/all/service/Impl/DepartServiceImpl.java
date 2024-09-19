package wh.fcfz.officecontroller.all.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.Depart;
import wh.fcfz.officecontroller.all.mapper.DepartMapper;
import wh.fcfz.officecontroller.all.service.DepartService;
import wh.fcfz.officecontroller.all.tool.ResponseEnum;
import wh.fcfz.officecontroller.all.tool.Result;

import java.util.List;
import java.util.stream.Collectors;

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
        LambdaQueryWrapper<Depart> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(depart.getDepartName() != null, Depart::getDepartName, depart.getDepartName())
                .eq(depart.getDepartTelephone() != null, Depart::getDepartTelephone, depart.getDepartTelephone())
                .eq(depart.getDepartEmail() != null, Depart::getDepartEmail, depart.getDepartEmail())
                .eq(depart.getStatus() != null, Depart::getStatus, depart.getStatus());
        Page<Depart> departmentPage = departMapper.selectPage(page, lambdaQueryWrapper);
        if (departmentPage.getRecords().isEmpty()) {
            log.info("分页查询部门列表结果为空");
            return new Result(ResponseEnum.DEPT_ID_NULL, departmentPage);
        }

        // 使用 Stream API 来统计每个部门的人数，并设置到每个部门对象中
        List<Depart> departmentsWithCounts = departmentPage.getRecords().stream()
                .peek(dep -> dep.setEmployeeCount(departMapper.countByDepartmentId(dep.getDepartId())))
                .collect(Collectors.toList());

        // 将更新后的部门列表设置回分页对象中
        departmentPage.setRecords(departmentsWithCounts);

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
        // 合并重复检查，减少冗余
        if (depart == null || depart.getDepartName() == null) {
            log.error("部门信息为空");
            return new Result(ResponseEnum.INVALID_PARAM, null);
        }
        LambdaQueryWrapper<Depart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Depart::getDepartName, depart.getDepartName());
        if (departMapper.selectOne(lambdaQueryWrapper) != null) {
            log.error("该部门已被创建");
            return new Result(ResponseEnum.DEPT_EXIST, depart);
        }

        depart.setCtTime(DateTime.now().toTimestamp());

        // 插入操作并检查结果
        try {
            if (departMapper.insert(depart) > 0) {
                return new Result(ResponseEnum.SUCCESS, depart);
            } else {
                log.error("部门信息保存失败，depart: {}", JSONUtil.toJsonStr(depart));
                return new Result(ResponseEnum.DEPT_SAVE_FAILED, depart);
            }
        } catch (Exception e) { // 统一处理其他异常
            log.error("保存部门信息时出现异常，depart: {}", depart, e);
            return new Result(ResponseEnum.DEPT_SAVE_FAILED, null);
        }
    }

    @Override
    public Result<Depart> updateDept(Depart depart) {
        LambdaUpdateWrapper<Depart> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(Depart::getUpTime, DateTime.now().toTimestamp())
                .eq(Depart::getDepartId, depart.getDepartId());
        if(departMapper.update(depart,lambdaUpdateWrapper)>0){
            log.info("部门更新成功");
            return new Result(ResponseEnum.SUCCESS,null);
        }else {
            log.error("部门更新失败, 该部门不存在");
            return new Result(ResponseEnum.DEPT_NOT_EXIST,null);
        }
    }

    @Override
    public Result<String> deleteDeptsBatch(List<Integer> ids) {

        if(ids == null || ids.isEmpty()) {
            log.error("删除部门信息为空");
            return new Result(ResponseEnum.DEPT_ID_NULL, null);
        }
        try {
            if(departMapper.deleteBatchIds(ids)>0){
                log.info("部门删除成功");
                return new Result(ResponseEnum.SUCCESS,null);
            }else {
                log.error("部门删除失败，该部门不存在");
                return new Result(ResponseEnum.DEPT_NOT_EXIST,null);
            }
        } catch (Exception e) {
            log.error("删除部门信息时出现异常，depart: {}", ids, e);
            return new Result(ResponseEnum.DELETE_SERVER_FAILED,null);
        }
    }

}
