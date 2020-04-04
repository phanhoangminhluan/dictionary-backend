package com.luanphm.dictionarybackend.repository.hint;

import com.luanphm.dictionarybackend.entity.Hint;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;

public interface HintRepository {
    List<Hint> getHints(String word);

    List<Hint> getAll(int page, SortOrder sortOrder);

    List<Hint> getAsc(int page);

    List<Hint> getDesc(int page);

    void deleteByTerm(String value);
}
