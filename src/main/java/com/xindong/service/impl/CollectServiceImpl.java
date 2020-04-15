package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.CollectMapper;
import com.xindong.entities.Collect;
import com.xindong.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    /**
     * 添加收藏
     *
     * @param collect
     * @return
     */
    @Transactional
    public boolean addCollection(Collect collect) {
        int insert = 0;
        try {
            insert = collectMapper.insert(collect);
        } catch (Exception e) {
            log.error("[添加收藏]-[添加失败]");
            return false;
        }
        return insert > 0 ? true : false;
    }

    /**
     * 判断歌曲id是否存在
     *
     * @param songId
     * @param userId
     * @return
     */
    public boolean existSongId(Integer songId, Integer userId) {
        Integer integer = 0;
        try {
            QueryWrapper<Collect> wrapper = new QueryWrapper<>();
            wrapper.eq("song_id", songId).eq("user_id", userId);
            integer = collectMapper.selectCount(wrapper);
        } catch (Exception e) {
            log.error("[判断歌曲id是否存在]-[查询失败]");
            return false;
        }
        return integer > 0 ? true : false;
    }

    /**
     * 更新歌曲
     *
     * @param collect
     * @return
     */
    public boolean updateCollectMsg(Collect collect) {
        log.info("运用mybatis进行更新");
        return collectMapper.updateCollectMsg(collect) > 0 ? true : false;
    }

    /**
     * 删除收藏歌曲
     *
     * @param id
     * @param userId
     * @return
     */
    public boolean deleteCollect(Integer id, Integer userId) {
        int i = 0;
        try {
            QueryWrapper<Collect> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId).eq("song_id", id);
            i = collectMapper.delete(wrapper);
        } catch (Exception e) {
            log.error("[删除收藏歌曲]-[查询失败]");
            return false;
        }
        return i > 0 ? true : false;
    }

    /**
     * 查询所有收藏 并按时间倒序排列
     *
     * @return
     */
    public List<Collect> allCollect() {
        List<Collect> collects = new ArrayList<>();
        try {
            QueryWrapper<Collect> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("create_time");
            collects = collectMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[查询所有收藏]-[查询失败]");
            return null;
        }
        return collects;
    }

    /**
     * 用户收藏的歌曲
     *
     * @param userId
     * @return
     */
    public List<Collect> myCollectOfSongs(Integer userId) {
        List<Collect> collects = new ArrayList<>();
        try {
            QueryWrapper<Collect> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            collects = collectMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[用户收藏的歌曲]-[查询失败]");
            return null;
        }
        return collects;
    }
}
