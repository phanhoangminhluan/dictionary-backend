package com.luanphm.dictionarybackend.repository.favorite_word;

import com.luanphm.dictionarybackend.entity.FavoriteWord;

import java.util.List;

public interface FavoriteWordRepository {

    List<FavoriteWord> getWords();
    boolean addWord(FavoriteWord favoriteWord);
    boolean deleteWord(String word);

}
