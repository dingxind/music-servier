package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.SongListMapper;
import com.xindong.entities.SongList;
import com.xindong.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean updateSongListMsg(SongList songList) {
        return songListMapper.updateSongListMsg(songList) >0 ?true:false;
    }

    @Override
    public boolean deleteSongList(Integer id) {
        return songListMapper.deleteById(id) >0 ?true:false;
    }

    @Override
    public List<SongList> listSongLists() {
        return songListMapper.selectList(null);
    }

    @Override
    public List<SongList> likeTitle(String title) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("title",title);
        return songListMapper.selectList(wrapper);
    }

    @Override
    public List<SongList> likeStyle(String style) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("style",style);
        return songListMapper.selectList(wrapper);
    }

    @Override
    public List<SongList> songAlbum(String title) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        return songListMapper.selectList(wrapper);
    }

    @Override
    public boolean ifAdd(SongList songList) {
        return songListMapper.insert(songList) > 0?true:false;
    }

    @Override
    public boolean updateSongListImg(SongList songList) {
        return songListMapper.updateSongListImg(songList) >0 ?true:false;
    }
}
