package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.FavoriteWordDTO;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.entity.FavoriteWord;
import com.luanphm.dictionarybackend.mapping.FavoriteWordMapping;
import com.luanphm.dictionarybackend.repository.favorite_word.FavoriteWordRepository;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteWordServiceImpl implements FavoriteWordService{

    @Autowired
    private FavoriteWordRepository favoriteWordRepository;

    @Autowired
    private WordDetailService wordDetailService;

    private FavoriteWordMapping favoriteWordMapping = Mappers.getMapper(FavoriteWordMapping.class);

    @Override
    public List<FavoriteWordDTO> getWords() {
        List<FavoriteWord> favoriteWords = favoriteWordRepository.getWords();
        if (favoriteWords.size() == 0 || favoriteWords == null) return null;
        return favoriteWordMapping.toDtos(favoriteWords);
    }

    @Override
    public boolean addWord(String word) {
        if (word == null) return false;

        WordDetailDTO wordDetail = wordDetailService.getWord(word);

        if (wordDetail == null) return false;

        String username = SecurityUtils.getCurrentUser();
        FavoriteWord favoriteWord = FavoriteWord.builder()
                .id(username + "_" + word)
                .word(word)
                .userId(username)
                .createdDate(CommonUtilities.getCurrentDateTime())
                .build();
        return favoriteWordRepository.addWord(favoriteWord);
    }

    @Override
    public boolean deleteWord(String word) {
        if (word == null || word.isEmpty()) return false;

        return favoriteWordRepository.deleteWord(word);
    }
}
