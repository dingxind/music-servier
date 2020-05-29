package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.Consumer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsumerMapper extends BaseMapper<Consumer> {

    int updateUserMsg(Consumer record);

    int updateUserAvator(Consumer record);

}