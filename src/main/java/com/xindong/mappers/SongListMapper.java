package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.SongList;
import org.springframework.stereotype.Repository;

@Repository
public interface SongListMapper extends BaseMapper<SongList> {

    int updateSongListMsg(SongList record);

    int updateSongListImg(SongList record);
}
