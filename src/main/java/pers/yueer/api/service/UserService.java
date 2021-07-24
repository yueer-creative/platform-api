package pers.yueer.api.service;

import pers.yueer.api.entity.Admin;
import pers.yueer.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yueer
 * @since 2021-03-09
 */
public interface UserService extends IService<User> {
    User getUserByUserAccount(String userAccount) throws Exception;
}
