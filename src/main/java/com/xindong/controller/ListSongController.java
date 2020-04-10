package com.xindong.controller;

import com.alibaba.fastjson.JSONObject;
import com.xindong.common.Result;
import com.xindong.entities.ListSong;
import com.xindong.service.impl.ListSongServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = "我的歌单")
public class ListSongController {

    @Autowired
    private ListSongServiceImpl listSongService;

    //    给歌单添加歌曲
    @ApiOperation("给歌单添加歌曲")
    @PostMapping(value = "/api/addListSong")
    public Result addListSong(@RequestParam("songId")  String song_id,
                              @RequestParam("songListId") String song_list_id) {
        ListSong listsong = new ListSong();
        listsong.setSongId(Integer.parseInt(song_id.trim()));
        listsong.setSongListId(Integer.parseInt(song_list_id.trim()));

        boolean res = listSongService.ifAdd(listsong);
        if (res) {;
            return  Result.ok().code(1).msg("修改成功");
        } else {
            return  Result.error().code(0).msg("修改失败");
        }
    }

    //    删除歌单里的歌曲
    @ApiOperation("删除歌单里的歌曲")
    @GetMapping(value = "/api/deleteListOfSong/{songId}")
    public Object deleteListOfSong(@PathVariable  String songId) {
        boolean deleteListSong = listSongService.deleteListSong(Integer.parseInt(songId));
        return Result.ok().data(deleteListSong);
    }

    //    更新歌单里面的歌曲信息
    @ApiOperation("更新歌单里面的歌曲信息")
    @PostMapping(value = "/api/updateListSongMsgs")
    public Result updateListSongMsgs(@RequestBody ListSong listsong) {

        boolean res = listSongService.updateListSongMsg(listsong);
        if (res) {
            return  Result.ok().msg("修改成功").code(1);
        } else {
            return  Result.error().code(0).msg("修改失败");
        }
    }

    //    返回歌单里包含的所有歌曲
    @ApiOperation("返回歌单里包含的所有歌曲")
    @GetMapping(value = "/allListSongs")
    public Result allListSongs() {
        List<ListSong> songs = listSongService.allListSong();
        return Result.ok().data(songs);
    }

    //    返回歌单里指定歌单ID的歌曲
    @ApiOperation("返回歌单里指定歌单ID的歌曲")
    @GetMapping(value = "/listSongOfSingers/{songListId}")
    public Result toSongList(@PathVariable String songListId) {
        List<ListSong> songs = listSongService.listSongsOfSingers(Integer.parseInt(songListId));
        return Result.ok().data(songs);
    }
}
