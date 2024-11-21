package wh.fcfz.officecontroller.all.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wh.fcfz.officecontroller.all.bean.Dao.Position;
import wh.fcfz.officecontroller.all.bean.Dao.User;
import wh.fcfz.officecontroller.all.bean.Dao.UsersPosition;
import wh.fcfz.officecontroller.all.bean.Vo.UsersPositionVo;
import wh.fcfz.officecontroller.all.mapper.PositionMapper;
import wh.fcfz.officecontroller.all.mapper.UserMapper;
import wh.fcfz.officecontroller.all.mapper.UsersPositionMapper;
import wh.fcfz.officecontroller.all.service.PositionService;
import wh.fcfz.officecontroller.all.tool.MyException;
import wh.fcfz.officecontroller.all.tool.MyPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @description 针对表【position(岗位信息表，用于存储公司内部各个岗位的基本信息)】的数据库操作Service实现
 * @createDate 2024-11-21 09:56:54
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position>
        implements PositionService {

    private final PositionMapper positionMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final UsersPositionMapper usersPositionMapper;
    private final UserMapper userMapper;
    Page<Position> page = new Page<>(1, 20);

    public PositionServiceImpl(PositionMapper positionMapper, StringRedisTemplate stringRedisTemplate, UsersPositionMapper usersPositionMapper, UserMapper userMapper) {
        this.positionMapper = positionMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.usersPositionMapper = usersPositionMapper;
        this.userMapper = userMapper;
    }

    public Page<Position> selectPosition(MyPage<Position> myPage) {
        if (stringRedisTemplate.opsForValue().get("position:" + JSONUtil.toJsonStr(myPage)) != null) {
            String s = stringRedisTemplate.opsForValue().get("position:" + JSONUtil.toJsonStr(myPage));
            Page bean = JSONUtil.toBean(s, Page.class);
            List<Position> positions = BeanUtil.copyToList(bean.getRecords(), Position.class);
            return page.setRecords(positions);
        }
        return getPositionPage(myPage);
    }

    private Page<Position> getPositionPage(MyPage<Position> myPage) {
        page = new Page<>(myPage.getPageNum(), myPage.getPageSize());
        LambdaQueryWrapper<Position> positionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (myPage.getData() != null)
            positionLambdaQueryWrapper
                    .like(myPage.getData().getName() != null, Position::getName, myPage.getData().getName());
        Page<Position> positionPage = positionMapper.selectPage(page, positionLambdaQueryWrapper);
        stringRedisTemplate.opsForValue()
                .set("position:" + JSONUtil.toJsonStr(myPage), JSONUtil.toJsonStr(page), 60, TimeUnit.SECONDS);
        return positionPage;
    }

    public Position getSelfPosition() {
        UsersPosition usersPosition = usersPositionMapper.selectById(StpUtil.getLoginIdAsInt());
        return positionMapper.selectById(usersPosition.getPosition());
    }

    @Transactional
    public boolean updatePosition(Position position) {
        int i = positionMapper.updateById(position);
        if (i > 0) {
            extracted();
            return true;
        } else {
            throw new MyException("修改失败", "10707");
        }
    }

    public Boolean deleteById(Long id) {
        if(positionMapper.deleteById(id) > 0){
            extracted();
            return true;
        }else {
            throw new MyException("没有该岗位，无法删除", "10708");
        }
    }

    private void extracted() {
        MyPage<Position> myPage = new MyPage<>();
        myPage.setPageNum(1);
        myPage.setPageSize(20);
        stringRedisTemplate.opsForValue()
                .set("position:" + JSONUtil.toJsonStr(page), JSONUtil.toJsonStr(getPositionPage(myPage)),60, TimeUnit.SECONDS);
    }

    //查询某个职位的所有用户
    public List<UsersPositionVo> getUserByPosition(Long id){
        Page<UsersPosition> page = new Page<>(1, 20);
        Page<UsersPosition> usersPositionPage = usersPositionMapper.selectPage(page, new LambdaQueryWrapper<UsersPosition>().eq(UsersPosition::getPosition, id));
        return usersPositionPage.getRecords().stream()
                .map(usersPosition -> {
                    UsersPositionVo usersPositionVo = new UsersPositionVo();
                    BeanUtil.copyProperties(usersPosition, usersPositionVo);
                    User user = userMapper.selectById(usersPosition.getUserId());
                    usersPositionVo.setImageUrl(user.getUserImage());
                    usersPositionVo.setUserName(user.getUserName());
                    return usersPositionVo;
                })
                .toList();
    }

    public Boolean savePosition(Position position) {
        Position one = this
                .getOne(new LambdaQueryWrapper<Position>()
                        .eq(Position::getName, position.getName())
                        .eq(Position::getDescription, position.getDescription()));
        if(one!=null){
            throw new MyException("该岗位已存在,id为"+one.getId(), "10706");
        }
        extracted();
        return positionMapper.insert(position) > 0;
    }
}




