package pers.yueer.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.yueer.api.common.response.AdminResult;
import pers.yueer.api.common.response.ResponseData;
import pers.yueer.api.entity.Admin;
import pers.yueer.api.service.AdminService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yueer
 * @since 2021-03-15
 */
@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseData adminLogin(@RequestBody Admin admin) throws Exception {
        Admin manager = adminService.getAdminByAdminAccount(admin.getAdminAccount());
        AdminResult adminResult = new AdminResult();
        ResponseData responseData = new ResponseData();


        if (manager == null) {
            System.out.println("消息:" + responseData.getMessage());
        } else {
            if (manager.getAdminPwd().equals(admin.getAdminPwd())){
                adminResult.setToken(manager.getAdminAccount());
                return responseData.success("登录成功").data("data", adminResult);

            }else{
                return responseData.error("密码错误");
            }
        }
        return responseData.error("没有该用户");
    }

}

