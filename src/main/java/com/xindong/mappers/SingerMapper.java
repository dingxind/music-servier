package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerMapper extends BaseMapper<Singer> {

    int updateSingerMsg(Singer record);

    int updateSingerImg(Singer record);
}
