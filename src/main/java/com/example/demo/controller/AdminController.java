package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.impl.AdminServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Api(tags = "Admin账号登录")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping(value = "/api/loginadmin")
    @ApiOperation("判断登录是否成功")
    public Object loginadmin(HttpServletRequest req, HttpSession session){

        JSONObject jsonObject = new JSONObject();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        //判断是否有这个用户
        boolean res = adminService.veritypasswd(name, password);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "登录成功");
            session.setAttribute("name", name);
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "用户名或密码错误");
            return jsonObject;
        }

    }
}
