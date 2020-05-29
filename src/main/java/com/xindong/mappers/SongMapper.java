package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.Song;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SongMapper extends BaseMapper<Song> {

    int updateSongMsg(Song record);

    int updateSongUrl(Song record);

    int updateSongPic(Song record);

}
