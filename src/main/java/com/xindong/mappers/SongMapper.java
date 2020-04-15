package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.xindong.entities.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMapper extends BaseMapper<Song> {

    int updateSongMsg(Song record);

    int updateSongUrl(Song record);

    int updateSongPic(Song record);

}
