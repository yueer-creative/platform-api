package pers.yueer.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import pers.yueer.api.common.response.ResponseData;
import pers.yueer.api.common.response.UserResult;
import pers.yueer.api.entity.User;
import pers.yueer.api.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yueer
 * @since 2021-03-09
 */
@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/usersList")
    public ResponseData list(@RequestParam(required = false) String query,
                             @RequestParam(defaultValue = "1") Integer current,
                             @RequestParam(defaultValue = "10") Integer limit) {

        ResponseData responseData = new ResponseData();

        //1.构建分页查询对象
        Page<User> usersPage = new Page<>(current, limit);
        usersPage.getPages();
        //2.构建查询参数
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("user_date");

        System.out.println("query:" + query);
        if (!StringUtils.isEmpty(query)) {
            wrapper.like("user_name", query);
        }
        userService.page(usersPage, wrapper);
        long total = usersPage.getTotal();
        List<User> records = usersPage.getRecords();
        System.out.println("records:" + records);

        return responseData.success("获取用户列表成功").data("total", total).data("records", records);
    }

    @PostMapping("/userStatusChange/{userId}/{userStatus}")
    public ResponseData userStatusChange(@PathVariable Integer userId,
                                         @PathVariable Integer userStatus) {

        ResponseData responseData = new ResponseData();
        User user = userService.getById(userId);
        user.setUserStatus(userStatus);

        userService.updateById(user);
        return responseData.success("更改用户状态成功");
    }

    @PostMapping("/userAdd")
    public ResponseData addUser(@RequestBody User user) {
        ResponseData responseData = new ResponseData();
        user.setUserDate(new Date());
        userService.save(user);
        return responseData.success("添加成功");
    }

    @PostMapping("/userUpdate")
    public ResponseData updateUser(@RequestBody User user) {
        ResponseData responseData = new ResponseData();
        user.setUserDate(new Date());
        userService.updateById(user);
        return responseData.success("修改成功");
    }

    @PostMapping("/userDelete/{userId}")
    public ResponseData deleteById(@PathVariable Integer userId) {
        ResponseData responseData = new ResponseData();
        userService.removeById(userId);
        return responseData.success("删除成功");
    }

    /* User */
    @PostMapping("/register")
    public ResponseData register(@RequestBody User user) {
        ResponseData responseData = new ResponseData();

        if ((user.getUserAccount().isEmpty()) || (user.getUserPwd().isEmpty()) || (user.getUserName().isEmpty())) {
            return responseData.error("请输入完整的用户信息");
        } else {
            user.setUserDate(new Date());
            userService.save(user);
            return responseData.success("注册成功");
        }
    }

    @PostMapping("/userLogin")
    public ResponseData userLogin(@RequestBody User user) throws Exception {
        User userResult = userService.getUserByUserAccount(user.getUserAccount());
        //UserResult userResult = new UserResult();
        ResponseData responseData = new ResponseData();

        if (userResult == null) {
            System.out.println("消息:" + responseData.getMessage());
        } else {
            if (userResult.getUserPwd().equals(user.getUserPwd())) {
                // userResult.setToken(userResult.getUserAccount());

                return responseData.success("登录成功").data("data", userResult);

            } else {
                return responseData.error("密码错误");
            }
        }
        return responseData.error("没有该用户");
    }

}

