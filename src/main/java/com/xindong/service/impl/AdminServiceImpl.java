package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xindong.entities.Admin;
import com.xindong.mappers.AdminMapper;
import com.xindong.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 判断账号是否存在
     *
     * @param name
     * @param password
     * @return
     */
    public boolean veritypasswd(String name, String password) {
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq("name",name).eq("password",password);
        log.info("进行数据库查询");
//        Integer integer = adminMapper.selectCount(wrapper);

        Integer integer = adminMapper.selectCount(new QueryWrapper<Admin>()
                .lambda().eq(Admin::getName, name).eq(Admin::getPassword, password));
        return integer > 0 ? true : false;
    }
}
