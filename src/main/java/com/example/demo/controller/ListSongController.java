package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.ListSong;
import com.example.demo.service.impl.ListSongServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "我的歌单")
public class ListSongController {

    @Autowired
    private ListSongServiceImpl listSongService;

    //    给歌单添加歌曲
    @ApiOperation("给歌单添加歌曲")
    @PostMapping(value = "/api/addListSong")
    public Object addListSong(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String song_id = req.getParameter("songId").trim();
        String song_list_id = req.getParameter("songListId").trim();

        ListSong listsong = new ListSong();
        listsong.setSongId(Integer.parseInt(song_id));
        listsong.setSongListId(Integer.parseInt(song_list_id));

        boolean res = listSongService.ifAdd(listsong);
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

    //    删除歌单里的歌曲
    @ApiOperation("删除歌单里的歌曲")
    @GetMapping(value = "/api/deleteListOfSong/{songId}")
    public Object deleteListOfSong(@PathVariable  String songId) {
//        String songId = req.getParameter("songId");
        return listSongService.deleteListSong(Integer.parseInt(songId));
    }

    //    更新歌单里面的歌曲信息
    @ApiOperation("更新歌单里面的歌曲信息")
    @PostMapping(value = "/api/updateListSongMsgs")
    public Object updateListSongMsgs(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String song_id = req.getParameter("songId").trim();
        String song_list_id = req.getParameter("songListId").trim();

        ListSong listsong = new ListSong();
        listsong.setId(Integer.parseInt(id));
        listsong.setSongId(Integer.parseInt(song_id));
        listsong.setSongListId(Integer.parseInt(song_list_id));

        boolean res = listSongService.updateListSongMsg(listsong);
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

    //    返回歌单里包含的所有歌曲
    @ApiOperation("返回歌单里包含的所有歌曲")
    @GetMapping(value = "/allListSongs")
    public Object allListSongs() {
        return listSongService.allListSong();
    }

    //    返回歌单里指定歌单ID的歌曲
    @ApiOperation("返回歌单里指定歌单ID的歌曲")
    @GetMapping(value = "/listSongOfSingers/{songListId}")
    public Object toSongList(@PathVariable String songListId) {
//        String songListId = req.getParameter("songListId");
        return listSongService.listSongsOfSingers(Integer.parseInt(songListId));
    }
}
