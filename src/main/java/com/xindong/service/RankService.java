package com.xindong.service;

import com.xindong.entities.Rank;

public interface RankService {

    int selectAverScore(Long songListId);

    Integer insert(Rank rank);
}
