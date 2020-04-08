package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.CollectMapper;
import com.xindong.entities.Collect;
import com.xindong.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean addCollection(Collect collect) {
        int insert = collectMapper.insert(collect);
        return insert > 0 ? true : false;
    }

    @Override
    public boolean existSongId(Integer songId, Integer userId) {
        return collectMapper.existSongId(songId, userId) > 0 ? true : false;
    }

    @Override
    public boolean updateCollectMsg(Collect collect) {
        return collectMapper.updateCollectMsg(collect) > 0 ? true : false;
    }

    @Override
    public boolean deleteCollect(Integer id,Integer userId) {
        int i = 0;
        try {
            QueryWrapper<Collect> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userId).eq("song_id",id);
            i = collectMapper.delete(wrapper);
        } catch (Exception e) {
            throw e;
        }
        return i > 0 ? true : false;
    }

    @Override
    public List<Collect> allCollect() {
        return collectMapper.allCollect();
    }

    @Override
    public List<Collect> myCollectOfSongs(Integer userId) {
        return collectMapper.myCollectOfSongs(userId);
    }
}
