package pers.yueer.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.yueer.api.entity.Admin;
import pers.yueer.api.mapper.AdminMapper;
import pers.yueer.api.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yueer
 * @since 2021-03-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    public Admin getAdminByAdminAccount(String account) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();

        return getBaseMapper().selectOne(wrapper.eq("admin_account", account));
    }

}
