package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.ListSongMapper;
import com.xindong.entities.ListSong;
import com.xindong.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public List<ListSong> allListSong() {
        List<ListSong> listSongs = listSongMapper.selectList(null);
        return listSongs;
    }

    @Override
    public boolean updateListSongMsg(ListSong listSong) {
        return listSongMapper.updateListSongMsg(listSong) >0 ?true:false;
    }

    @Override
    public boolean deleteListSong(Integer songId) {
        QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
        wrapper.eq("songId",songId);
        int delete = listSongMapper.delete(wrapper);
        return delete >0 ?true:false;
    }

    @Override
    public boolean ifAdd(ListSong listSong) {
        int insert = listSongMapper.insert(listSong);
        return insert > 0?true:false;
    }

    @Override
    public List<ListSong> listSongsOfSingers(Integer songListId) {
        List<ListSong> listSongs = new ArrayList<>();
        try{
            QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
            wrapper.eq("song_list_id",songListId);
            listSongs = listSongMapper.selectList(wrapper);
        } catch(Exception e) {
            throw e;
        }
        return listSongs;
    }

}
