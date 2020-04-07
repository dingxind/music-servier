package com.xindong.controller;

import com.alibaba.fastjson.JSONObject;
import com.xindong.common.Result;
import com.xindong.entities.Collect;
import com.xindong.service.impl.CollectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Api(tags = "用户收藏接口")
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

    //    添加收藏的歌曲
    @PostMapping(value = "/api/addCollections")
    @ApiOperation(" 添加收藏的歌曲")
    public Result addCollections(@RequestBody Collect collect) {

        if (collect.getType() == 0) {
            collect.setSongId(collect.getSongId());
        } else if (collect.getType() == 1) {
            collect.setSongListId(collect.getSongListId());
        }
        collect.setCreateTime(new Date());
        boolean res = collectService.addCollection(collect);
        if (res) {
            return Result.ok().msg("收藏成功").code(1);
        } else {
            return Result.ok().msg("收藏失败").code(0);
        }
    }

    @PostMapping(value = "/api/collectionList")
    public Result collectionList(@RequestBody Collect collect) {
        try {
            if (ObjectUtils.isEmpty(collect.getSongId())) {
                return Result.error().code(0).msg("收藏失败");
            } else if (collectService.existSongId(collect.getSongId(), collect.getUserId())) {
                return Result.error().code(2).msg("已收藏");
            }
            if (collect.getType() == 0) {
                collect.setSongId(collect.getSongId());
            } else if (collect.getType() == 1) {
                collect.setSongListId(collect.getSongListId());
            }
            collect.setCreateTime(new Date());
            boolean res = collectService.addCollection(collect);
            if (res) {
                return Result.ok().code(1).msg("收藏成功");
            } else {
                return Result.error().code(0).msg("收藏失败");
            }
        } catch(Exception e) {
            throw  e;
        }
    }

    //    删除收藏的歌曲
    @ApiOperation("删除收藏的歌曲")
    @GetMapping(value = "/api/deleteCollects/{id}")
    public Object deleteCollects(@PathVariable String id) {
//        String id = req.getParameter("id");
        return collectService.deleteCollect(Integer.parseInt(id));
    }

    //    更新收藏
    @PostMapping(value = "/api/updateCollectMsgs")
    @ApiOperation("更新收藏")
    public Object updateCollectMsgs(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String type = req.getParameter("type").trim();
        String song_id = req.getParameter("songId").trim();
//        String song_list_id = req.getParameter("songListId").trim();

        Collect collect = new Collect();
        collect.setId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(song_id));

        boolean res = collectService.updateCollectMsg(collect);
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

    //    返回所有用户收藏列表
    @ApiOperation("返回所有用户收藏列表")
    @GetMapping(value = "/allCollects")
    public Object allCollects() {
        return collectService.allCollect();
    }

    //    返回的制定用户ID收藏列表
    @ApiOperation("返回的制定用户ID收藏列表")
    @GetMapping(value = "/myCollection/{userId}")
    public Object myCollection(@PathVariable String userId) {
//        String userId = req.getParameter("userId");
        return collectService.myCollectOfSongs(Integer.parseInt(userId));
    }
}
