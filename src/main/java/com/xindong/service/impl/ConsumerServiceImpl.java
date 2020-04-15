package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.ConsumerMapper;
import com.xindong.entities.Consumer;
import com.xindong.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(Consumer consumer) {
        int insert = 0;
        try{
         insert = consumerMapper.insert(consumer);
        } catch(Exception e) {
            throw e;
        }
        return insert >0 ?true:false;
    }

    @Override
    public boolean updateUserMsg(Consumer consumer) {
        return consumerMapper.updateUserMsg(consumer) >0 ?true:false;
    }

    @Override
    public boolean updateUserAvator(Consumer consumer) {

        return consumerMapper.updateUserAvator(consumer) >0 ?true:false;
    }

    @Override
    public boolean existUser(String username) {
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Integer integer = consumerMapper.selectCount(wrapper);
        return integer>0 ? true:false;
    }

    @Override
    public boolean veritypasswd(String username, String password) {
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        Integer integer = consumerMapper.selectCount(wrapper);
        return integer>0?true:false;
    }

//    删除用户
    @Override
    public boolean deleteUser(Integer id) {
        int i = consumerMapper.deleteById(id);
        return i >0 ?true:false;
    }

    @Override
    public List<Consumer> allUser(){
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        List<Consumer> consumers = consumerMapper.selectList(wrapper);
        return consumers;
    }

    @Override
    public boolean ifAdd(Consumer consumer) {
        int insert = consumerMapper.insert(consumer);
        return insert > 0?true:false;
    }

    @Override
    public List<Consumer> conmmentUser(Integer id) {
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        List<Consumer> consumers = consumerMapper.selectList(wrapper);
        return consumers;
    }

    @Override
    public List<Consumer> consumerLists(String username) {
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        List<Consumer> consumers = consumerMapper.selectList(wrapper);
        return consumers;
    }
}
