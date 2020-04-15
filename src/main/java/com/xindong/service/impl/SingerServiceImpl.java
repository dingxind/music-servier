package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.SingerMapper;
import com.xindong.entities.Singer;
import com.xindong.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    /**
     * 更新歌手
     *
     * @param singer
     * @return
     */
    public boolean updateSingerMsg(Singer singer) {
        log.info("[更新歌手]-[调用mybatis方法]");
        return singerMapper.updateSingerMsg(singer) > 0 ? true : false;
    }

    /**
     * 更新歌手图片
     *
     * @param singer
     * @return
     */
    public boolean updateSingerImg(Singer singer) {
        log.info("[更新歌手图片]-[调用mybatis方法]");
        return singerMapper.updateSingerImg(singer) > 0 ? true : false;
    }

    /**
     * 删除歌手
     *
     * @param id
     * @return
     */
    public boolean deleteSinger(Integer id) {
        int i = 0;
        try {
            i = singerMapper.deleteById(id);
        } catch (Exception e) {
            log.error("[删除歌手]-[删除失败]");
            return false;
        }
        return i > 0 ? true : false;
    }

    /**
     * 查询列表
     *
     * @return
     */
    public List<Singer> listSingers() {
        List<Singer> singers = new ArrayList<>();
        try {
            singers = singerMapper.selectList(null);
        } catch (Exception e) {
            log.error("[查询列表]-[查询失败]");
            return null;
        }
        return singers;
    }

    /**
     * 添加歌手
     *
     * @param singer
     * @return
     */
    public boolean ifAdd(Singer singer) {
        int insert = 0;
        try {
            insert = singerMapper.insert(singer);
        } catch (Exception e) {
            log.error("[添加歌手]-[添加失败]");
            return false;
        }
        return insert > 0 ? true : false;
    }

    /**
     * 根据名字进行模糊查询
     *
     * @param name
     * @return
     */
    public List<Singer> searachSinger(String name) {
        List<Singer> singers = new ArrayList<>();
        try {
            QueryWrapper<Singer> wrapper = new QueryWrapper<>();
            wrapper.like("name", name);
            singers = singerMapper.selectList(wrapper);
        } catch (Exception e) {
            log.error("[根据名字进行模糊查询]-[查询失败]");
            return null;
        }
        return singers;
    }

    /**
     * 根据性别查询列表
     *
     * @param sex
     * @return
     */
    public List<Singer> singerSex(Integer sex) {
        List<Singer> singers = new ArrayList<>();
        try{
            QueryWrapper<Singer> wrapper = new QueryWrapper<>();
            wrapper.eq("sex", sex);
            singers = singerMapper.selectList(wrapper);
        } catch(Exception e) {
            log.error("[根据名字进行模糊查询]-[查询失败]");
            return null;
        }
        return singers;
    }
}
