package com.luanphm.dictionarybackend.repository.hint;

import com.luanphm.dictionarybackend.entity.Hint;

import java.util.List;

public interface HintRepository {
    List<Hint> getHints(String word);
}
