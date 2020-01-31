package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.mapping.CardSetMapping;
import com.luanphm.dictionarybackend.repository.card_set.CardSetRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardSetServiceImpl extends MyAbstractService<CardSet, String, CardSetDTO> implements CardSetService{

    @Autowired
    private CardSetRepository cardSetRepository;

    @Override
    protected void inject() {
        this.repository = cardSetRepository;
        this.mappingHandler = Mappers.getMapper(CardSetMapping.class);
    }
}
