package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.ExceptionConstants;
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
    public List<FavoriteWordDTO> getWords() throws Exception {
        List<FavoriteWord> favoriteWords = favoriteWordRepository.getWords();
        if (favoriteWords == null || favoriteWords.size() == 0) {
            throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);
        }
        return favoriteWordMapping.toDtos(favoriteWords);
    }

    @Override
    public boolean addWord(String word) throws Exception {
        if (word == null) {
            throw new Exception(ExceptionConstants.INPUT_ERROR);
        }

        WordDetailDTO wordDetail = wordDetailService.getWord(word);

        if (wordDetail == null) {
            throw new Exception(ExceptionConstants.NO_OBJECT_FOUND);
        }

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
    public boolean deleteWord(String word) throws Exception {
        if (word == null || word.isEmpty()) {
            throw new Exception(ExceptionConstants.INPUT_ERROR);
        }

        return favoriteWordRepository.deleteWord(word);
    }
}
