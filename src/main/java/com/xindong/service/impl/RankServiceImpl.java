package com.xindong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xindong.mappers.RankMapper;
import com.xindong.entities.Rank;
import com.xindong.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    /**
     * 计算评分
     *
     * @param songListId
     * @return
     */
    public int selectAverScore(Long songListId) {
        int sum = 0;
        List<Rank> ranks = new ArrayList<>();
        try {
            QueryWrapper<Rank> wrapper = new QueryWrapper<>();
            wrapper.eq("song_list_id", songListId);
            ranks = rankMapper.selectList(wrapper);
            if (ranks.size() == 0) {
                return 0;
            }
            List<Integer> collect = ranks.parallelStream().map(Rank::getScore).collect(Collectors.toList());
            for (int i : collect) {
                sum += i;
            }
        } catch (Exception e) {
            log.error("[计算评分]-[计算评分失败]");
            return 0;
        }
        return sum / ranks.size();
    }

    /**
     * 添加评价
     *
     * @param rank
     * @return
     */
    public Integer insert(Rank rank) {
        int insert = 0;
        try {
            List<Rank> ranks = rankMapper.selectList(new QueryWrapper<Rank>().lambda()
                    .eq(Rank::getSongListId, rank.getSongListId())
                    .eq(Rank::getConsumerId, rank.getConsumerId()));
            if (!CollectionUtils.isEmpty(ranks)) {
                insert = 2;
                return insert;
            }

            insert = rankMapper.insert(rank);

        } catch (Exception e) {
            log.error("[添加评价]-[评价失败]");
            throw e;
        }
        return insert;
    }
}
