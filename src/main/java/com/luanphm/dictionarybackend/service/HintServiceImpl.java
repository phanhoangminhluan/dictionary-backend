package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.entity.Hint;
import com.luanphm.dictionarybackend.repository.hint.HintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HintServiceImpl implements HintService {

    @Autowired
    private HintRepository hintRepository;

    @Override
    public List<String> getHints(String word) {
        List<Hint> hints = hintRepository.getHints(word);
        List<String> hintArr = new ArrayList<>();
        hints.forEach(hint -> hintArr.add(hint.getWord()));
        return hintArr;
    }
}
