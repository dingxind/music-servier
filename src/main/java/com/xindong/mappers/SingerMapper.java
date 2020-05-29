package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.Singer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SingerMapper extends BaseMapper<Singer> {

    int updateSingerMsg(Singer record);

    int updateSingerImg(Singer record);
}
