package com.xindong.service;

import com.xindong.entities.ListSong;

import java.util.List;

public interface ListSongService {

    boolean ifAdd(ListSong listSong);

    boolean updateListSongMsg(ListSong listSong);

    boolean deleteListSong(Integer songId);

    List<ListSong> allListSong();

    List<ListSong> listSongsOfSingers(Integer songListId);
}
