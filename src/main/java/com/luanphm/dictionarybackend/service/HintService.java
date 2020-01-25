package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.entity.Hint;

import java.util.List;

public interface HintService {

    List<String>  getHints(String word);

}
