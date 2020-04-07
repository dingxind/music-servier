package com.xindong.service;

import com.xindong.entities.Collect;

import java.util.List;

public interface CollectService {

    boolean addCollection(Collect collect);

    boolean existSongId(Integer songId, Integer userId);

    boolean updateCollectMsg(Collect collect);

    boolean deleteCollect(Integer id);

    List<Collect> allCollect();

    List<Collect> myCollectOfSongs(Integer userId);
}
