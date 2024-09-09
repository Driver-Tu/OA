package wh.fcfz.officecontroller.all.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.fcfz.officecontroller.all.bean.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.service.DepartService;
import wh.fcfz.officecontroller.all.mapper.DepartMapper;

import java.util.List;

@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements DepartService {

    @Autowired
    private DepartMapper departMapper;

    @Override
    public Result<Depart> SelectPageALL(Integer pageNum, Integer pageSize) {
        Page<Depart> page = new Page<>(pageNum, pageSize);

        // 查询分页结果
        return new Result(ResponseEnum.SUCCESS,departMapper.selectPage(page, null)); // null 表示无查询条件
    }
}
