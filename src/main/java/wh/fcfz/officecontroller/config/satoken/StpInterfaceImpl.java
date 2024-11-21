package wh.fcfz.officecontroller.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import wh.fcfz.officecontroller.all.service.Impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object o, String s) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        if(stringRedisTemplate.opsForValue().get("permission:"+o.toString())!=null){
            String s1 = stringRedisTemplate.opsForValue().get("permission:"+o.toString());
            return JSONUtil.toList(s1,String.class);
        }
        List<String> list = new ArrayList<String>();
        list.add(userService.SelectByUserId().getData().getRoleName());
        stringRedisTemplate.opsForValue().set("permission:"+o.toString(), JSONUtil.toJsonStr(list));
        return list;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        if(stringRedisTemplate.opsForValue().get("role:"+o.toString())!=null){
            String s1 = stringRedisTemplate.opsForValue().get("role:"+o.toString());
            return JSONUtil.toList(s1,String.class);
        }
        List<String> list = new ArrayList<String>();
        list.add(userService.getById(StpUtil.getLoginIdAsLong()).getRoleId().toString());
        stringRedisTemplate.opsForValue().set("role:"+o.toString(), JSONUtil.toJsonStr(list));
        return list;
    }
}
