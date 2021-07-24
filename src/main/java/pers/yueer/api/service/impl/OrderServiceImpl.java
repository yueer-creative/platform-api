package pers.yueer.api.service.impl;

import pers.yueer.api.entity.Order;
import pers.yueer.api.mapper.OrderMapper;
import pers.yueer.api.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yueer
 * @since 2021-04-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
