package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.SongMapper;
import com.xindong.entities.Song;
import com.xindong.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    /**
     * 查询列表 按时间倒序排列
     *
     * @return
     */
    public List<Song> listSongs() {
        List<Song> songs = new ArrayList<>();
        try {
            QueryWrapper<Song> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("create_time");
            songs = songMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[查询列表]-[查询失败]");
            return null;
        }
        return songs;
    }

    /**
     * 添加歌曲
     *
     * @param song
     * @return
     */
    public boolean ifAdd(Song song) {
        int insert = 0;
        try {
            insert = songMapper.insert(song);
        } catch (Exception e) {
            log.error("[添加歌曲]-[添加失败]");
            return false;
        }
        return insert > 0 ? true : false;
    }

    /**
     * 更新歌曲歌词
     *
     * @param song
     * @return
     */
    public boolean updateSongMsg(Song song) {
        log.info("[更新歌曲歌词]-[运用mybatis方法]");
        return songMapper.updateSongMsg(song) > 0 ? true : false;
    }

    /**
     * 更新歌曲地址
     *
     * @param song
     * @return
     */
    public boolean updateSongUrl(Song song) {
        log.info("[更新歌曲地址]-[运用mybatis方法]");
        return songMapper.updateSongUrl(song) > 0 ? true : false;
    }

    /**
     * 更新歌曲照片
     *
     * @param song
     * @return
     */
    public boolean updateSongPic(Song song) {
        log.info("[更新歌曲照片]-[运用mybatis方法]");
        return songMapper.updateSongPic(song) > 0 ? true : false;
    }

    /**
     * 删除歌曲
     *
     * @param id
     * @return
     */
    public boolean deleteSong(Integer id) {
        int i = 0;
        try {
            i = songMapper.deleteById(id);
        } catch (Exception e) {
            log.error("[删除歌曲]-[删除失败]");
            return false;
        }
        return i > 0 ? true : false;
    }

    /**
     * 根据singerId查询列表
     *
     * @param singerId
     * @return
     */
    public List<Song> listSongsOfSinger(Integer singerId) {
        List<Song> songs = new ArrayList<>();
        try {
            QueryWrapper<Song> wrapper = new QueryWrapper<>();
            wrapper.eq("singer_id", singerId);
            songs = songMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[根据singerId查询列表]-[查询失败]");
            return null;
        }
        return songs;
    }

    /**
     * 根据id查询列表
     *
     * @param id
     * @return
     */
    public List<Song> listSongsOfSongs(Integer id) {
        List<Song> songs = new ArrayList<>();
        try {
            QueryWrapper<Song> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id);
            songs = songMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[根据id查询列表]-[查询失败]");
            return null;
        }
        return songs;
    }

    /**
     * 根据名字模糊查询列表
     *
     * @param name
     * @return
     */
    public List<Song> searachSongLists(String name) {
        List<Song> songs = new ArrayList<>();
        try {
            QueryWrapper<Song> wrapper = new QueryWrapper<>();
            wrapper.like("name", name);
            songs = songMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[根据名字模糊查询列表]-[查询失败]");
            return null;
        }
        return songs;
    }

    /**
     * 根据名字精确查找
     *
     * @param name
     * @return
     */
    public List<Song> songOfName(String name) {
        List<Song> songs = new ArrayList<>();
        try {
            QueryWrapper<Song> wrapper = new QueryWrapper<>();
            wrapper.eq("name", name);
            songs = songMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[根据名字精确查找]-[查询失败]");
            return null;
        }
        return songs;
    }
}
