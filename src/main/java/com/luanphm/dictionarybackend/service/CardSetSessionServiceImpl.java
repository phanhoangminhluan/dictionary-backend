package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.entity.CardSetSession;
import com.luanphm.dictionarybackend.mapping.CardSetSessionMapping;
import com.luanphm.dictionarybackend.repository.card_set_session.CardSetSessionRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardSetSessionServiceImpl extends MyAbstractService<CardSetSession, String, CardSetSessionDTO> implements CardSetSessionService{

    @Autowired
    private CardSetSessionRepository cardSetSessionRepository;

    @Override
    protected void inject() {
        this.repository = cardSetSessionRepository;
        this.mappingHandler = Mappers.getMapper(CardSetSessionMapping.class);
    }
}
