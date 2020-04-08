package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
//    int deleteByPrimaryKey(Integer id);

//    int insert(Collect record);

//    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

//    int addCollection(Collect collect);

    int existSongId(Integer songId,Integer userId);

    int updateCollectMsg(Collect collect);

//    int deleteCollect(Integer id);

    List<Collect> allCollect();

    List<Collect> myCollectOfSongs(Integer userId);
}