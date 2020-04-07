package com.xindong.service.impl;

import com.xindong.mappers.RankMapper;
import com.xindong.entities.Rank;
import com.xindong.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public int selectAverScore(Long songListId) {
        return rankMapper.selectScoreSum(songListId)/rankMapper.selectRankNum(songListId);
    }

    @Override
    public boolean insert(Rank rank) {

        return rankMapper.insertSelective(rank) > 0 ? true:false;
    }
}
