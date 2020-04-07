package com.xindong.service.impl;

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
        return commentMapper.addComment(comment) > 0 ? true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        return commentMapper.updateCommentMsg(comment) >0 ?true:false;
    }

//    删除评论
    @Override
    public boolean deleteComment(Integer id) {
        return commentMapper.deleteComment(id) >0 ?true:false;
    }

    @Override
    public List<Comment> songListComment()
    {
        return commentMapper.songListComment();
    }

    @Override
    public List<Comment> songComments(Integer songId)

    {
        return commentMapper.songComments(songId);
    }

    @Override
    public List<Comment> songListComments(Integer songListId)

    {
        return commentMapper.songListComments(songListId);
    }
}
