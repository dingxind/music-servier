package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.CommentMapper;
import com.xindong.entities.Comment;
import com.xindong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {

        int insert = commentMapper.insert(comment);
        return insert > 0 ? true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        return commentMapper.updateCommentMsg(comment) >0 ?true:false;
    }

//    删除评论
    @Override
    public boolean deleteComment(Integer id) {
        int i = commentMapper.deleteById(id);
        return i >0 ?true:false;
    }

    @Override
    public List<Comment> songListComment() {
        List<Comment> comments = commentMapper.selectList(null);
        return comments;
    }

    @Override
    public List<Comment> songComments(Integer songId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id",songId);
        List<Comment> comments = commentMapper.selectList(wrapper);
        return comments;
    }

    @Override
    public List<Comment> songListComments(Integer songListId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id",songListId);
        List<Comment> comments = commentMapper.selectList(wrapper);
        return comments;
    }
}
