package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.FavoriteWordDTO;

import java.util.List;

public interface FavoriteWordService {

    List<FavoriteWordDTO> getWords();

    boolean addWord(String word);

    boolean deleteWord(String word);



}
