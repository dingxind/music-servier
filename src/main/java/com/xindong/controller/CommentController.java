package com.xindong.controller;

import com.alibaba.fastjson.JSONObject;
import com.xindong.entities.Comment;
import com.xindong.service.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Api(tags = "评论接口")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    // 提交评论
    @ApiOperation("提交评论")
    @PostMapping(value = "/api/commentList")
    public Object commentList(HttpServletRequest req) {

        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String song_list_id = req.getParameter("songListId");
        String song_id = req.getParameter("songId");
        String comtent = req.getParameter("comtent").trim();

        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(user_id));
        comment.setType(new Byte(type));
        if (new Byte(type) == 0) {
            comment.setSongId(Integer.parseInt(song_id));
        } else if (new Byte(type) == 1) {
            comment.setSongListId(Integer.parseInt(song_list_id));
        }
        comment.setContent(comtent);
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评论成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评论失败");
            return jsonObject;
        }
    }

    //    点赞
    @ApiOperation("点赞")
    @PostMapping(value = "/api/postUp")
    public Object postUp(HttpServletRequest req) {

        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String up = req.getParameter("up").trim();

        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        boolean res = commentService.updateCommentMsg(comment);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "点赞成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "点赞失败");
            return jsonObject;
        }
    }

    //    删除评论
    @ApiOperation("删除评论")
    @GetMapping(value = "/api/deleteComments/{id}")
    public Object deleteComments(@PathVariable String id) {
//        String id = req.getParameter("id");
        return commentService.deleteComment(Integer.parseInt(id));
    }

    //    更新评论
    @ApiOperation("更新评论")
    @PostMapping(value = "/api/updateCommentMsgs")
    public Object updateCommentMsgs(@RequestBody Comment comment) {
        JSONObject jsonObject = new JSONObject();
//        String id = req.getParameter("id").trim();
//        String user_id = req.getParameter("userId").trim();
//        String song_id = req.getParameter("songId").trim();
//        String song_list_id = req.getParameter("songListId").trim();
//        String content = req.getParameter("content").trim();
//        String type = req.getParameter("type").trim();
//        String up = req.getParameter("up").trim();
//
//        Comment comment = new Comment();
//        comment.setId(Integer.parseInt(id));
//        comment.setUserId(Integer.parseInt(user_id));
//        if (song_id == "") {
//            comment.setSongId(null);
//        } else {
//            comment.setSongId(Integer.parseInt(song_id));
//        }
//
//        if (song_list_id == "") {
//            comment.setSongListId(null);
//        } else {
//            comment.setSongListId(Integer.parseInt(song_list_id));
//        }
//        comment.setContent(content);
//        comment.setType(new Byte(type));
//        comment.setUp(Integer.parseInt(up));

        boolean res = commentService.updateCommentMsg(comment);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }

    //    获取所有评论列表
    @ApiOperation("获取所有评论列表")
    @GetMapping(value = "/songsListComment")
    public Object songsListComment() {
        return commentService.songListComment();
    }

    //    获得指定歌曲ID的评论列表
    @ApiOperation("获得指定歌曲ID的评论列表")
    @GetMapping(value = "/songComments/{songId}")
    public Object songComments(@PathVariable String songId) {
//        String songId = req.getParameter("songId");
        return commentService.songComments(Integer.parseInt(songId));
    }

    //    获得指定歌单ID的评论列表
    @ApiOperation("获得指定歌单ID的评论列表")
    @GetMapping(value = "/songListComments/{songListId}")
    public Object songListComments(@PathVariable String songListId) {
//        String songListId = req.getParameter("songListId");
        return commentService.songListComments(Integer.parseInt(songListId));
    }
}
