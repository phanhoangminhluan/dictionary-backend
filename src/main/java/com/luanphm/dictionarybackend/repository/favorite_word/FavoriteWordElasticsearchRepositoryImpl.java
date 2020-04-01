package com.luanphm.dictionarybackend.repository.favorite_word;

import com.luanphm.dictionarybackend.constant.ElasticFields;
import com.luanphm.dictionarybackend.constant.ElasticIndexes;
import com.luanphm.dictionarybackend.constant.ExceptionConstants;
import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.entity.FavoriteWord;
import com.luanphm.dictionarybackend.handler.ElasticSearchHandler;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FavoriteWordElasticsearchRepositoryImpl extends ElasticSearchHandler<FavoriteWord> implements FavoriteWordRepository {

    @Override
    public List<FavoriteWord> getWords() throws Exception {
        String username = SecurityUtils.getCurrentUser();
        if (username == null) return null;

        Map<String, Object> termValueMust = new HashMap<>();
        termValueMust.put(ElasticFields.USER_ID, username);
        List<FavoriteWord> favoriteWords = getMany(termValueMust, null, null, ElasticFields.CREATED_DATE);

        if (favoriteWords == null || favoriteWords.size() == 0) throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);

        return favoriteWords;
    }

    @Override
    public boolean addWord(FavoriteWord favoriteWord) throws Exception {

        if (!create(favoriteWord)) throw new Exception(ExceptionConstants.ERROR_WHEN_ADDED);

        return true;
    }

    @Override
    public boolean deleteWord(String word) throws Exception {
        String username = SecurityUtils.getCurrentUser();
        String id = username + "_" + word;
        if (!deleteById(id)) throw new Exception(ExceptionConstants.ERROR_WHEN_DELETE);
        return true;
    }

    @Override
    public Class<FavoriteWord> getIndexClass() {
        return FavoriteWord.class;
    }

    @Override
    public String getIndexName() {
        return ElasticIndexes.FAVORITE_WORD;
    }
}
