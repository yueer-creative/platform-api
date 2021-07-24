package pers.yueer.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.yueer.api.entity.Admin;
import pers.yueer.api.entity.User;
import pers.yueer.api.mapper.UserMapper;
import pers.yueer.api.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yueer
 * @since 2021-03-09
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public User getUserByUserAccount(String userAccount) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        System.out.println(userAccount);
        return getBaseMapper().selectOne(wrapper.eq("user_account", userAccount));
    }
}


