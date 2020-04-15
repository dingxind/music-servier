package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.Collect;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectMapper extends BaseMapper<Collect> {

    int updateCollectMsg(Collect collect);
}