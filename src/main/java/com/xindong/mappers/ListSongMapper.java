package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.ListSong;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ListSongMapper extends BaseMapper<ListSong> {

    int updateByPrimaryKey(ListSong record);

    int updateListSongMsg(ListSong record);
}
