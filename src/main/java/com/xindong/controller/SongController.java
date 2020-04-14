package com.xindong.controller;

import com.alibaba.fastjson.JSONObject;
import com.xindong.common.Result;
import com.xindong.entities.Song;
import com.xindong.service.impl.SongServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "歌曲接口")
public class SongController {

    @Autowired
    private SongServiceImpl songService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    //    添加歌曲
    @ApiOperation("添加歌曲")
    @PostMapping(value = "/api/addSong")
    public Result addSong(@RequestParam("singerId") String singer_id,
                          @RequestParam("name") String name,
                          @RequestParam("introduction") String introduction,
                          @RequestParam("lyric") String lyric,
                          @RequestParam("file") MultipartFile mpfile) {
        String pic = "/img/songPic/tubiao.jpg";

        if (mpfile.isEmpty()) {
            return Result.error().code(0).msg("音乐上传失败！");
        }
        String fileName = mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            mpfile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean res = songService.ifAdd(song);
            if (res) {
                return Result.ok().code(1).avator(storeUrlPath).msg("上传成功");
            } else {
                return Result.error().code(0).msg("上传失败");
            }
        } catch (IOException e) {
            return  Result.error().code(0).msg("上传失败"+ e.getMessage());
        }
    }

    //    更新歌曲图片
    @ApiOperation("更新歌曲图片")
    @PostMapping(value = "/api/updateSongPic")
    public Result updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {

        if (urlFile.isEmpty()) {
            return Result.error().code(0).msg("音乐上传失败！");
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/img/songPic/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeUrlPath);
            boolean res = songService.updateSongPic(song);
            if (res) {
                return Result.ok().code(1).avator(storeUrlPath).msg("上传成功");
            } else {
                return Result.error().code(0).msg("音乐上传失败！");
            }
        } catch (IOException e) {
            return  Result.error().code(0).msg("上传失败"+ e.getMessage());
        }
    }

    //    更新歌曲URL
    @ApiOperation("更新歌曲URL")
    @PostMapping(value = "/api/updateSongUrl")
    public Result updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {

        if (urlFile.isEmpty()) {
            return Result.error().code(0).msg("音乐上传失败！");
        }
        String fileName = urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean res = songService.updateSongUrl(song);
            if (res) {
                return Result.ok().code(1).avator(storeUrlPath).msg("上传成功");
            } else {
                return Result.error().code(0).msg("音乐上传失败！");
            }
        } catch (IOException e) {
            return  Result.error().code(0).msg("上传失败"+ e.getMessage());
        }
    }


    //    删除歌曲
    @ApiOperation("删除歌曲")
    @GetMapping(value = "/api/deleteSongs/{id}")
    public Result deleteSongs(@PathVariable  String id) {
        boolean deleteSong = songService.deleteSong(Integer.parseInt(id));
        return Result.ok().data(deleteSong);
    }

    //    更新歌曲信息
    @ApiOperation("更新歌曲信息")
    @PostMapping(value = "/api/updateSongMsgs")
    public Result updateSongMsgs(@RequestBody Song song ) {;

        boolean res = songService.updateSongMsg(song);
        if (res) {
            return  Result.ok().code(1).msg("修改成功").data(res);
        } else {
            return  Result.error().code(0).msg("修改失败").data(res);
        }
    }

    //    返回所有歌曲
    @ApiOperation("返回所有歌曲")
    @GetMapping(value = "/AllSongs")
    public Result AllSongs() {
        List<Song> songs = songService.listSongs();
        return Result.ok().data(songs);
    }

    //    返回指定歌曲ID的歌曲
    @ApiOperation("返回指定歌曲ID的歌曲")
    @GetMapping(value = "/listSongsOfSongs/{id}")
    public Result toSongs(@PathVariable String id) {
        List<Song> songs = songService.listSongsOfSongs(Integer.parseInt(id));
        return Result.ok().data(songs);
    }

    //    返回指定歌手ID的歌曲
    @ApiOperation("返回指定歌手ID的歌曲")
    @GetMapping(value = "/listSongs/{singerId}")
    public Result toSongList(@PathVariable String singerId) {
        List<Song> songs = songService.listSongsOfSinger(Integer.parseInt(singerId));
        return Result.ok().data(songs);
    }

    //    返回指定歌手名的歌曲
    @ApiOperation("返回指定歌手名的歌曲")
    @GetMapping(value = "/listSongsOfSearch/{name}")
    public Result toSearchLists(@PathVariable String name) {
        List<Song> songs = songService.searachSongLists('%' + name + '%');
        return Result.ok().data(songs);
    }

    @ApiOperation("根据歌名查找歌曲")
    @GetMapping(value = "/api/song/{name}")
    public Result songOfName(@PathVariable String name) {
        List<Song> songs = songService.songOfName(name.trim());
        return Result.ok().data(songs);
    }
}
