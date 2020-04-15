package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.CommentMapper;
import com.xindong.entities.Comment;
import com.xindong.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 添加列表
     * @param comment
     * @return
     */
    @Transactional
    public boolean addComment(Comment comment) {
        int insert = 0;
        try{
            insert = commentMapper.insert(comment);
        } catch(Exception e) {
            log.error("[添加列表]-[添加失败]");
            return false;
        }
        return insert > 0 ? true : false;
    }

    /**
     * 更新列表
     * @param comment
     * @return
     */
    @Transactional
    public boolean updateCommentMsg(Comment comment) {
        log.info("[更新列表]-[mybatis方法]");
        return commentMapper.updateCommentMsg(comment) > 0 ? true : false;
    }


    /**
     *  删除评论
     * @param id
     * @return
     */
    public boolean deleteComment(Integer id) {
        int i = 0;
        try{
            i = commentMapper.deleteById(id);
        } catch(Exception e) {
            log.error("[删除评论]-[删除失败]");
            return false;
        }
        return i > 0 ? true : false;
    }

    /**
     * 查询所有列表
     * @return
     */
    public List<Comment> songListComment() {
        List<Comment> comments = new ArrayList<>();
        try{
            QueryWrapper<Comment> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("create_time");
            comments = commentMapper.selectList(wrapper);
        } catch(Exception e) {
            log.error("[查询所有列表]-[查询失败]");
            return null;
        }
        return comments;
    }

    /**
     * 根据songId进行查询
     * @param songId
     * @return
     */
    public List<Comment> songComments(Integer songId) {
        List<Comment> comments = new ArrayList<>();
        try{
            QueryWrapper<Comment> wrapper = new QueryWrapper<>();
            wrapper.eq("song_id", songId);
           comments = commentMapper.selectList(wrapper);
        } catch(Exception e) {
            log.error("[根据songId进行查询]-[查询失败]");
            return  null;
        }
        return comments;
    }

    /**
     *根据songListId进行查询
     * @param songListId
     * @return
     */
    public List<Comment> songListComments(Integer songListId) {
        List<Comment> comments = new ArrayList<>();
        try{
            QueryWrapper<Comment> wrapper = new QueryWrapper<>();
            wrapper.eq("song_list_id", songListId);
            comments = commentMapper.selectList(wrapper);
        } catch(Exception e) {
            log.error("[根据songListId进行查询]-[查询失败]");
            return  null;
        }
        return comments;
    }
}
