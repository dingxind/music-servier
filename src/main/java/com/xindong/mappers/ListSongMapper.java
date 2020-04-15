package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListSongMapper extends BaseMapper<ListSong> {

    int updateByPrimaryKey(ListSong record);

    int updateListSongMsg(ListSong record);
}
