package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.SongListMapper;
import com.xindong.entities.SongList;
import com.xindong.service.SongListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    /**
     * 更新歌单列表
     *
     * @param songList
     * @return
     */
    public boolean updateSongListMsg(SongList songList) {
        log.info("[更新歌单列表]-[运用mybatis方法]");
        return songListMapper.updateSongListMsg(songList) > 0 ? true : false;
    }

    /**
     * 根据id删除歌单
     *
     * @param id
     * @return
     */
    public boolean deleteSongList(Integer id) {
        int i = 0;
        try {
            i = songListMapper.deleteById(id);
        } catch (Exception e) {
            log.error("[根据id删除歌单]-[删除失败]");
            return false;
        }
        return i > 0 ? true : false;
    }

    /**
     * 查询歌曲列表
     *
     * @return
     */
    public List<SongList> listSongLists() {
        List<SongList> songLists = new ArrayList<>();
        try {
            songLists = songListMapper.selectList(null);
        } catch (Exception e) {
            log.error("[查询歌曲列表]-查询失败]");
            return null;
        }
        return songLists;
    }

    /**
     * 根据title进行模糊查询
     *
     * @param title
     * @return
     */
    public List<SongList> likeTitle(String title) {
        List<SongList> songLists = new ArrayList<>();
        try {
            QueryWrapper<SongList> wrapper = new QueryWrapper<>();
            wrapper.like("title", title);
            songLists = songListMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[根据title进行模糊查询]-查询失败]");
            return null;
        }
        return songLists;
    }

    /**
     * 根据style进行模糊查询
     *
     * @param style
     * @return
     */
    public List<SongList> likeStyle(String style) {
        List<SongList> songLists = new ArrayList<>();
        try {
            QueryWrapper<SongList> wrapper = new QueryWrapper<>();
            wrapper.like("style", style);
            songLists = songListMapper.selectList(wrapper);

        } catch (Exception e) {
            log.error("[根据style进行模糊查询]-查询失败]");
            return null;
        }
        return songLists;
    }

    /**
     * 根据标题查询
     *
     * @param title
     * @return
     */
    public List<SongList> songAlbum(String title) {
        List<SongList> songLists = new ArrayList<>();
        try {
            QueryWrapper<SongList> wrapper = new QueryWrapper<>();
            wrapper.eq("title", title);
            songLists = songListMapper.selectList(wrapper);

        } catch (Exception e) {
            log.error("[根据标题查询]-查询失败]");
            return null;
        }
        return songLists;
    }

    /**
     * 添加歌单
     *
     * @param songList
     * @return
     */
    public boolean ifAdd(SongList songList) {
        int insert = 0;
        try {
            insert = songListMapper.insert(songList);
        } catch (Exception e) {
            log.error("[添加歌单]-[添加失败]");
            return false;
        }
        return insert > 0 ? true : false;
    }

    /**
     * 更新图片
     *
     * @param songList
     * @return
     */
    public boolean updateSongListImg(SongList songList) {
        log.info("[更新图片]-[运用mybatis方法]");
        return songListMapper.updateSongListImg(songList) > 0 ? true : false;
    }
}
