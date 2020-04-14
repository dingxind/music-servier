package com.xindong.controller;

import com.xindong.common.Result;
import com.xindong.entities.Comment;
import com.xindong.service.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "评论接口")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    // 提交评论
    @ApiOperation("提交评论")
    @PostMapping(value = "/api/commentList")
    public Result commentList(@RequestBody Comment comment) {

        if (comment.getType() == 0) {
            comment.setSongId(comment.getSongId());
        } else if (comment.getType() == 1) {
            comment.setSongListId(comment.getSongListId());
        }
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res) {
            return Result.ok().code(1).msg("评论成功");
        } else {
            return Result.error().code(0).msg("评论失败");
        }
    }

    //    点赞
    @ApiOperation("点赞")
    @PostMapping(value = "/api/postUp")
    public Result postUp(@RequestBody Comment comment) {
        boolean res = commentService.updateCommentMsg(comment);
        if (res) {
            return Result.ok().code(1).msg("点赞成功");
        } else {
            return Result.error().code(0).msg("点赞失败");
        }
    }

    //    删除评论
    @ApiOperation("删除评论")
    @GetMapping(value = "/api/deleteComments/{id}")
    public Result deleteComments(@PathVariable String id) {
        boolean b = commentService.deleteComment(Integer.parseInt(id));
        return Result.ok().data(b);
    }

    //    更新评论
    @ApiOperation("更新评论")
    @PostMapping(value = "/api/updateCommentMsgs")
    public Result updateCommentMsgs(@RequestBody Comment comment) {
        boolean res = commentService.updateCommentMsg(comment);
        if (res) {
            return Result.ok().code(1).msg("修改成功");
        } else {
            return Result.error().code(0).msg("修改失败");
        }
    }

    //    获取所有评论列表
    @ApiOperation("获取所有评论列表")
    @GetMapping(value = "/songsListComment")
    public Result songsListComment() {
        List<Comment> comments = commentService.songListComment();
        return Result.ok().data(comments);
    }

    //    获得指定歌曲ID的评论列表
    @ApiOperation("获得指定歌曲ID的评论列表")
    @GetMapping(value = "/songComments/{songId}")
    public Result songComments(@PathVariable String songId) {
        List<Comment> comments = commentService.songComments(Integer.parseInt(songId));
        return Result.ok().data(comments);
    }

    //    获得指定歌单ID的评论列表
    @ApiOperation("获得指定歌单ID的评论列表")
    @GetMapping(value = "/songListComments/{songListId}")
    public Result songListComments(@PathVariable String songListId) {
        List<Comment> comments = commentService.songListComments(Integer.parseInt(songListId));
        return Result.ok().data(comments);
    }
}
