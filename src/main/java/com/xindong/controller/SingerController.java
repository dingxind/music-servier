package com.xindong.controller;

import com.xindong.common.Result;
import com.xindong.entities.Singer;
import com.xindong.service.impl.SingerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@Api(tags = "歌手接口")
public class SingerController {

    @Autowired
    private SingerServiceImpl singerService;

    //    添加歌手
    @ApiOperation("添加歌手")
    @PostMapping(value = "/api/addSinger")
    public Result addSinger(@RequestBody Singer singer) {

        boolean res = singerService.ifAdd(singer);
        if (res) {
            return Result.ok().code(1).msg("添加成功");
        } else {
            return Result.error().code(0).msg("添加失败");
        }
    }

    //    删除歌手
    @ApiOperation("删除歌手")
    @GetMapping(value = "/api/deleteSingers/{id}")
    public Result deleteSingers(@PathVariable String id ) {
        boolean b = singerService.deleteSinger(Integer.parseInt(id));
        return Result.ok().data(b);
    }

    //    更新歌手信息
    @ApiOperation("更新歌手信息")
    @PostMapping(value = "/api/updateSingerMsgs")
    public Result updateSingerMsgs(@RequestBody Singer singer ) {

        boolean res = singerService.updateSingerMsg(singer);
        if (res) {
            return Result.ok().code(1).msg("修改成功");
        } else {
            return  Result.error().code(0).msg("修改失败");
        }
    }

    @ApiOperation("文件上传")
    @PostMapping(value = "/api/updateSingerImg")
    public Result updateSingerImg(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {

        if (avatorFile.isEmpty()) {
            return  Result.error().code(0).msg("文件上传失败！");
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean res = singerService.updateSingerImg(singer);
            if (res) {
                return Result.ok().code(1).msg("上传成功").data("pic",storeAvatorPath);
            } else {
                return Result.error().code(0).msg("上传失败");
            }
        } catch (IOException e) {
            return Result.error().code(0).msg("上传失败" + e.getMessage());
        }
    }

    //    返回所有歌手
    @ApiOperation("返回所有歌手")
    @GetMapping(value = "/listSingers")
    public Result toSingerList() {
        List<Singer> singers = singerService.listSingers();
        return Result.ok().data(singers);
    }

    //    根据歌手名查找歌手
    @ApiOperation("根据歌手名查找歌手")
    @GetMapping(value = "/searachSingers/{name}")
    public Result searachSingers(@PathVariable  String name) {
        List<Singer> singers = singerService.searachSinger(name.trim());
        return Result.ok().data(singers);
    }

    //    根据歌手性别查找歌手
    @ApiOperation("根据歌手性别查找歌手")
    @GetMapping(value = "/api/singer/{sex}")
    public Result SingerSex(@PathVariable String sex) {
        List<Singer> singers = singerService.singerSex(Integer.parseInt(sex.trim()));
        return Result.ok().data(singers);
    }
}
