package com.xindong.controller;

import com.xindong.common.Result;
import com.xindong.entities.SongList;
import com.xindong.service.impl.SongListServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@Api(tags = "歌单接口")
public class SongListController {

    @Autowired
    private SongListServiceImpl songListService;

    //    添加歌单
    @ApiOperation("添加歌单")
    @PostMapping(value = "/api/addSongList")
    public Result addSongList(@RequestBody SongList songList) {

        boolean res = songListService.ifAdd(songList);
        if (res) {
            return  Result.ok().code(1).msg("添加成功");
        } else {
            return  Result.error().code(0).msg("添加失败");
        }
    }

    //    删除歌单
    @ApiOperation("删除歌单")
    @GetMapping(value = "/api/deleteSongLists/{id}")
    public Result deleteSongLists(@PathVariable String id) {
        boolean b = songListService.deleteSongList(Integer.parseInt(id));
        return Result.ok().data(b);
    }

    //    更新歌单信息
    @ApiOperation("更新歌单信息")
    @PostMapping(value = "/api/updateSongListMsgs")
    public Result updateSongListMsgs(@RequestBody SongList songList) {

        boolean res = songListService.updateSongListMsg(songList);
        if (res) {
            return Result.ok().code(1).msg("修改成功");
        } else {
            return Result.error().code(0).msg("修改失败");
        }
    }

    //    更新歌单图片
    @ApiOperation("更新歌单图片")
    @PostMapping(value = "/api/updateSongListImg")
    public Result updateSongListImg(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        if (avatorFile.isEmpty()) {
            return Result.error().code(0).msg("文件上传失败！");
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean res = songListService.updateSongListImg(songList);
            if (res) {
                return Result.ok().code(1).avator(storeAvatorPath).msg("上传成功");
            } else {
                return Result.error().code(0).msg("上传失败");
            }
        } catch (IOException e) {
            return Result.error().code(0).msg("上传失败" + e.getMessage());
        }
    }



    //    返回指定标题对应的歌单
    @ApiOperation("返回指定标题对应的歌单")
    @GetMapping(value = "/api/songAlbum/{title}")
    public Result songAlbum(@PathVariable  String title) {
        List<SongList> songLists = songListService.songAlbum(title.trim());
        return Result.ok().data(songLists);
    }

    //    返回标题包含文字的歌单
    @ApiOperation("返回标题包含文字的歌单")
    @GetMapping(value = "/api/songList/likeTitle/{title}")
    public Result likeTitle(@PathVariable  String title) {
        List<SongList> songLists = songListService.likeTitle('%' + title.trim() + '%');
        return Result.ok().data(songLists);
    }

    //    返回指定类型的歌单
    @ApiOperation("返回指定类型的歌单")
    @GetMapping(value = "/api/songList/likeStyle/{style}")
    public Result likeStyle(@PathVariable  String style) {
        List<SongList> songLists = songListService.likeStyle(style.trim());
        return Result.ok().data(songLists);
    }

    //    返回所有歌单
    @ApiOperation("返回所有歌单")
    @GetMapping(value = "/listSongLists")
    public Result toSongList() {
        List<SongList> songLists = songListService.listSongLists();
        return Result.ok().data(songLists);
    }

}















