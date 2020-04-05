package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Rank;
import com.example.demo.service.impl.RankServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "评分接口")
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

    //    提交评分
    @ApiOperation("提交评分")
    @PostMapping(value = "/api/pushRank")
    public Object signup(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String songListId = req.getParameter("songListId").trim();
        String consumerId = req.getParameter("consumerId").trim();
        String score = req.getParameter("score").trim();

        Rank rank = new Rank();
        rank.setSongListId(Long.parseLong(songListId));
        rank.setConsumerId(Long.parseLong(consumerId));
        rank.setScore(Integer.parseInt(score));

        boolean res = rankService.insert(rank);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

    //    获取指定歌单的评分
    @ApiOperation("获取指定歌单的评分")
    @GetMapping(value = "/api/getRank")
    public Object ranks(HttpServletRequest req) {
        String songListId = req.getParameter("songListId");
        return rankService.selectAverScore(Long.parseLong(songListId));
    }
}
