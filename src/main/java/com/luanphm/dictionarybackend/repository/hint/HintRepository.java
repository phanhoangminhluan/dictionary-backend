package com.luanphm.dictionarybackend.repository.hint;

import com.luanphm.dictionarybackend.entity.Hint;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.List;

public interface HintRepository {
    List<Hint> getHints(String word);

    List<Hint> getAll(int page, SortOrder sortOrder) throws IOException;

    List<Hint> getAsc(int page) throws IOException;

    List<Hint> getDesc(int page) throws IOException;

    void deleteByTerm(String value);
}
