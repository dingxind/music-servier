package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.ConsumerMapper;
import com.xindong.entities.Consumer;
import com.xindong.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * 添加用户
     *
     * @param consumer
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(Consumer consumer) {
        int insert = 0;
        try {
            insert = consumerMapper.insert(consumer);
        } catch (Exception e) {
            log.error("[添加用户]-[添加失败]");
            throw e;
        }
        return insert > 0 ? true : false;
    }

    /**
     * 修改用户
     *
     * @param consumer
     * @return
     */
    public boolean updateUserMsg(Consumer consumer) {
        log.info("[修改用户]-[运用mybatis]");
        return consumerMapper.updateUserMsg(consumer) > 0 ? true : false;
    }

    /**
     * 更新用户
     *
     * @param consumer
     * @return
     */
    public boolean updateUserAvator(Consumer consumer) {
        log.info("[更新用户]-[运用mybatis]");
        return consumerMapper.updateUserAvator(consumer) > 0 ? true : false;
    }

    /**
     * 判断用户名否存在
     *
     * @param username
     * @return
     */
    public boolean existUser(String username) {
        Integer integer = 0;
        try {
            QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);
            integer = consumerMapper.selectCount(wrapper);
        } catch (Exception e) {
            log.error("[判断用户名否存在]-[查询失败]");
            return false;
        }
        return integer > 0 ? true : false;
    }

    /**
     * 判断用户是否登录成功
     *
     * @param username
     * @param password
     * @return
     */
    public boolean veritypasswd(String username, String password) {
        Integer integer = 0;
        try {
            QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username).eq("password", password);
            integer = consumerMapper.selectCount(wrapper);
        } catch (Exception e) {
            log.error("[判断用户是否登录成功]-[查询失败]");
            return false;
        }
        return integer > 0 ? true : false;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public boolean deleteUser(Integer id) {
        int i = 0;
        try {
            i = consumerMapper.deleteById(id);
        } catch (Exception e) {
            log.error("[删除用户]-[删除失败]");
            return false;
        }
        return i > 0 ? true : false;
    }

    /**
     * 查询列表 根据创建时间倒序排列
     *
     * @return
     */
    public List<Consumer> allUser() {
        List<Consumer> consumers = new ArrayList<>();
        try {
            QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("create_time");
            consumers = consumerMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[查询列表]-[查询失败]");
            return null;
        }
        return consumers;
    }

    /**
     * 根据id查询列表
     *
     * @param id
     * @return
     */
    public List<Consumer> conmmentUser(Integer id) {
        List<Consumer> consumers = new ArrayList<>();
        try {
            QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            consumers = consumerMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[查询列表]-[查询失败]");
            return null;
        }
        return consumers;
    }

    /**
     * 根据username查询列表
     *
     * @param username
     * @return
     */
    public List<Consumer> consumerLists(String username) {
        List<Consumer> consumers = new ArrayList<>();
        try {
            QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);
            consumers = consumerMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[查询列表]-[查询失败]");
            return null;
        }
        return consumers;
    }
}
