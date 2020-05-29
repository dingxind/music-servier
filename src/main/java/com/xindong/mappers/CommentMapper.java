package com.xindong.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xindong.entities.Comment;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    int updateByPrimaryKey(Comment record);

    int updateCommentMsg(Comment record);

}