package pers.yueer.api.service;

import pers.yueer.api.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yueer
 * @since 2021-03-15
 */
public interface AdminService extends IService<Admin> {

    Admin getAdminByAdminAccount(String account) throws Exception;

}
