package com.xindong.controller;

import com.xindong.common.Result;
import com.xindong.entities.Consumer;
import com.xindong.service.impl.ConsumerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "用户接口")
public class LoginController {

    @Autowired
    private ConsumerServiceImpl consumerService;

    //    添加用户
    @ApiOperation("添加用户")
    @PostMapping(value = "/api/signup")
    public Result signup(@Valid @RequestBody Consumer consumer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();
            return Result.error().code(0).msg(error);
        }
        String username = consumer.getUsername();

        if (username.equals("") || username == null) {
            return Result.error().code(0).msg("用户名或密码错误");
        }

        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());

        boolean res = consumerService.addUser(consumer);
        if (res) {
            return  Result.ok().code(1).msg("注册成功");
        } else {
            return Result.error().code(0).msg("用户名或密码错误");
        }
    }

    @ApiOperation("验证用户名是否重复")
    @GetMapping(value = "/api/repeatName/{username}")
    public  Result repeatName(@PathVariable String username){
        List<Consumer> consumers = consumerService.consumerLists(username);
        if(!CollectionUtils.isEmpty(consumers)){
            return Result.ok().code(1).data(consumers);
        }
        return Result.error().code(0).data(consumers);
    }

    @ApiOperation("判断是否登录成功")
    @PostMapping(value = "/api/loginVerify")
    public Result loginVerify(@Valid @RequestBody Consumer consumer,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();
            return Result.error().code(0).msg(error);
        }

        String username = consumer.getUsername();
        String password = consumer.getPassword();
        boolean res = consumerService.veritypasswd(username, password);

        if (res) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return  Result.ok().code(1).msg("登录成功").data("userMsg",consumerService.consumerLists(username));
        } else {
            return  Result.error().code(0).msg("用户名或密码错误");
        }

    }

    //    删除用户
    @ApiOperation("删除用户")
    @GetMapping(value = "/api/deleteUsers/{id}")
    public Result deleteUsers(@PathVariable String id) {
        boolean deleteUser = consumerService.deleteUser(Integer.parseInt(id));
        return Result.ok().data(deleteUser);
    }

    //    更新用户信息
    @ApiOperation("更新用户信息")
    @PostMapping(value = "/api/updateUserMsgs")
    public Result updateUserMsgs(@Valid @RequestBody  Consumer consumer,BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();
            return Result.error().code(0).msg(error);
        }

          String username = consumer.getUsername();

        if (username.equals("") || username == null) {
            return  Result.error().code(0).msg("用户名或密码错误");
        }
        consumer.setUpdateTime(new Date());

        boolean res = consumerService.updateUserMsg(consumer);
        if (res) {
            return  Result.ok().code(1).msg("修改成功");
        } else {
            return Result.error().code(0).msg("修改失败");
        }
    }

    //    更新用户头像
    @ApiOperation("更新用户头像")
    @PostMapping(value = "/api/updateUserImg")
    public Result updateUserImg(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {

        if (avatorFile.isEmpty()) {
            return  Result.error().code(0).msg("文件上传失败！");
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean res = consumerService.updateUserAvator(consumer);
            if (res) {
                return  Result.ok().code(1).avator(storeAvatorPath).msg("上传成功");
            } else {
                return Result.error().code(0).msg("上传失败");
            }
        } catch (IOException e) {
            return Result.error().code(0).msg("上传失败" + e.getMessage());
        }
    }



    //    返回指定ID的用户
    @ApiOperation("返回指定ID的用户")
    @GetMapping(value = "/commentOfConsumer/{id}")
    public Result toSongs(@PathVariable String id) {
        List<Consumer> consumers = consumerService.conmmentUser(Integer.parseInt(id));
        return Result.ok().data(consumers);
    }

    //    返回所有用户
    @ApiOperation("返回所有用户")
    @GetMapping(value = "/AllUsers")
    public Result AllUsers() {
        List<Consumer> consumers = consumerService.allUser();
        return Result.ok().data(consumers);
    }
}
