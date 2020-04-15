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
import java.util.List;

@RestController
@Api(tags = "用户收藏接口")
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

    @PostMapping(value = "/api/collectionList")
    public Result collectionList(@RequestBody Collect collect) {
        try {
            if (ObjectUtils.isEmpty(collect.getSongId())) {
                return Result.error().code(0).msg("收藏失败");
            } else if (collectService.existSongId(collect.getSongId(), collect.getUserId())) {
                return Result.error().code(2).msg("已收藏");
            }

            collect.setCreateTime(new Date());
            boolean res = collectService.addCollection(collect);
            if (res) {
                return Result.ok().code(1).msg("收藏成功");
            } else {
                return Result.error().code(0).msg("收藏失败");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @ApiOperation("删除收藏的歌曲")
    @GetMapping(value = "/api/deleteCollects/{id}/{userId}")
    public Result deleteCollects(@PathVariable String id, @PathVariable String userId) {
        Result result = null;
        boolean collect = collectService.deleteCollect(Integer.parseInt(id), Integer.parseInt(userId));
        if (collect == false) {
            result = Result.error().msg("删除失败").data(false);
        } else {
            result = Result.ok().msg("删除成功").data(true);
        }
        return result;
    }

    @PostMapping(value = "/api/updateCollectMsgs")
    @ApiOperation("更新收藏")
    public Object updateCollectMsgs(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String type = req.getParameter("type").trim();
        String song_id = req.getParameter("songId").trim();

        Collect collect = new Collect();
        collect.setId(id);
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

    @ApiOperation("返回所有用户收藏列表")
    @GetMapping(value = "/allCollects")
    public Result allCollects() {
        List<Collect> collects = collectService.allCollect();
        return Result.ok().data(collects);
    }

    @ApiOperation("返回的制定用户ID收藏列表")
    @GetMapping(value = "/myCollection/{userId}")
    public Result myCollection(@PathVariable String userId) {
        List<Collect> collects = collectService.myCollectOfSongs(Integer.parseInt(userId));
        return Result.ok().data(collects);
    }
}
