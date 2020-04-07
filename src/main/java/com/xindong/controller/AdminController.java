package com.xindong.controller;

import com.xindong.common.Result;
import com.xindong.entities.Admin;
import com.xindong.service.impl.AdminServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "Admin账号登录")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping(value = "/api/loginadmin")
    @ApiOperation("判断登录是否成功")
    public Result loginadmin(HttpServletRequest request, @RequestBody Admin admin){

        String name = admin.getName();
        String password = admin.getPassword();
        //判断是否有这个用户
        boolean res = adminService.veritypasswd(name, password);
        if (res){
            request.setAttribute("name", name);
            return Result.ok().code(1).msg("登录成功");
        }else {
            return Result.ok().code(0).msg("用户名或密码错误");
        }

    }
}
