package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.mapping.CardMapping;
import com.luanphm.dictionarybackend.repository.card.CardRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends MyAbstractService<Card, String, CardDTO> implements CardService{

    @Autowired
    private CardRepository cardRepository;

    @Override
    protected void inject() {
        this.repository = cardRepository;
        this.mappingHandler = Mappers.getMapper(CardMapping.class);

    }
}
