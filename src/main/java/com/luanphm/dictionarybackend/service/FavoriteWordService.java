package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.FavoriteWordDTO;

import java.util.List;

public interface FavoriteWordService {

    List<FavoriteWordDTO> getWords() throws Exception;

    boolean addWord(String word) throws Exception;

    boolean deleteWord(String word) throws Exception;



}
