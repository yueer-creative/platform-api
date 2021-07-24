package pers.yueer.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.yueer.api.common.response.ResponseData;
import pers.yueer.api.entity.Order;
import pers.yueer.api.service.OrderService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yueer
 * @since 2021-04-08
 */
@RestController
@RequestMapping("/api/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public ResponseData list(@RequestParam(required = false) String query,
                             @RequestParam(defaultValue = "1") Integer current,
                             @RequestParam(defaultValue = "10") Integer limit) {

        ResponseData responseData = new ResponseData();

        //1.构建分页查询对象
        Page<Order> orderPage = new Page<>(current, limit);
        orderPage.getPages();
        //2.构建查询参数
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("order_time");

        if (!StringUtils.isEmpty(query)) {
            wrapper.like("order_buyer_id", query);
        }

        orderService.page(orderPage, wrapper);
        long total = orderPage.getTotal();
        List<Order> records = orderPage.getRecords();
        System.out.println("records:" + records);

        return responseData.success("获取商品列表成功").data("total", total).data("records", records);
    }
}

