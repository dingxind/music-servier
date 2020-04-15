package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.SingerMapper;
import com.xindong.entities.Singer;
import com.xindong.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService{

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean updateSingerMsg(Singer singer) {
        return singerMapper.updateSingerMsg(singer) >0 ?true:false;
    }

    @Override
    public boolean updateSingerImg(Singer singer) {

        return singerMapper.updateSingerImg(singer) >0 ?true:false;
    }

    @Override
    public boolean deleteSinger(Integer id) {
        int i = singerMapper.deleteById(id);
        return i >0 ?true:false;
    }

    @Override
    public List<Singer> listSingers() {

        return singerMapper.selectList(null);
    }

    @Override
    public boolean ifAdd(Singer singer)  {

        return singerMapper.insert(singer) > 0 ? true : false;
    }

    @Override
    public List<Singer> searachSinger(String name) {
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        return singerMapper.selectList(wrapper);
    }

    @Override
    public List<Singer> singerSex(Integer sex) {
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.eq("sex",sex);
        return singerMapper.selectList(wrapper);
    }
}
