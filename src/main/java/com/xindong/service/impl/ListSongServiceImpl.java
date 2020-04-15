package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.ListSongMapper;
import com.xindong.entities.ListSong;
import com.xindong.service.ListSongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    /**
     * 查询列表
     * @return
     */
    public List<ListSong> allListSong() {
        List<ListSong> listSongs = new ArrayList<>();
        try{
            listSongs = listSongMapper.selectList(null);
        } catch(Exception e) {
            log.error("[查询列表]-[查询失败]");
            return null;
        }
        return listSongs;
    }

    /**
     *更新列表
     * @param listSong
     * @return
     */
    public boolean updateListSongMsg(ListSong listSong) {
        log.info("[更新列表]-[更新图片失败]");
        return listSongMapper.updateListSongMsg(listSong) >0 ?true:false;
    }

    /**
     *根据id删除
     * @param songId
     * @return
     */
    public boolean deleteListSong(Integer songId) {
        int delete = 0;
        try{
            QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
            wrapper.eq("songId",songId);
            delete = listSongMapper.delete(wrapper);
        } catch(Exception e) {
            log.error("[根据id删除]-[删除失败]");
            return false;
        }
        return delete >0 ?true:false;
    }

    /**
     *添加歌曲
     * @param listSong
     * @return
     */
    public boolean ifAdd(ListSong listSong) {
        int insert = 0;
        try{
            insert = listSongMapper.insert(listSong);
        } catch(Exception e) {
            log.info("[添加歌曲]-[添加失败]");
            return false;
        }
        return insert > 0?true:false;
    }

    /**
     *根据song_list_id查询列表
     * @param songListId
     * @return
     */
    public List<ListSong> listSongsOfSingers(Integer songListId) {
        List<ListSong> listSongs = new ArrayList<>();
        try{
            QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
            wrapper.eq("song_list_id",songListId);
            listSongs = listSongMapper.selectList(wrapper);
        } catch(Exception e) {
            log.error("[根据song_list_id查询列表]-[查询失败]");
            return  null;
        }
        return listSongs;
    }

}
