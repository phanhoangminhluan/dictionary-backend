package com.luanphm.dictionarybackend.repository.favorite_word;

import com.luanphm.dictionarybackend.entity.FavoriteWord;

import java.util.List;

public interface FavoriteWordRepository {

    List<FavoriteWord> getWords() throws Exception;
    boolean addWord(FavoriteWord favoriteWord) throws Exception;
    boolean deleteWord(String word) throws Exception;

}
