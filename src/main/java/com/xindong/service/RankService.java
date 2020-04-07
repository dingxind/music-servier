package com.xindong.service;

import com.xindong.entities.Rank;

public interface RankService {

    int selectAverScore(Long songListId);

    boolean insert(Rank rank);
}
