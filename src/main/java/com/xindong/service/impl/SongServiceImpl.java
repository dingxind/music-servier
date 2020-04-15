package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.SongMapper;
import com.xindong.entities.Song;
import com.xindong.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public List<Song> listSongs() {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        return songMapper.selectList(wrapper);
    }

    @Override
    public boolean ifAdd(Song song) {
        return songMapper.insert(song) > 0?true:false;
    }

    @Override
    public boolean updateSongMsg(Song song) {
        return songMapper.updateSongMsg(song) >0 ?true:false;
    }

    @Override
    public boolean updateSongUrl(Song song) {

        return songMapper.updateSongUrl(song) >0 ?true:false;
    }

    @Override
    public boolean updateSongPic(Song song) {

        return songMapper.updateSongPic(song) >0 ?true:false;
    }

    @Override
    public boolean deleteSong(Integer id) {
        return songMapper.deleteById(id) >0 ?true:false;
    }

    @Override
    public List<Song> listSongsOfSinger(Integer singerId) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("singer_id",singerId);
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> listSongsOfSongs(Integer id) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> searachSongLists(String name) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> songOfName(String name) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        return songMapper.selectList(wrapper);
    }
}
