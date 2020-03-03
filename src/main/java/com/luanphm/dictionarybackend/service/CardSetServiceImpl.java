package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.dto.CardSetInsertDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.mapping.CardSetMapping;
import com.luanphm.dictionarybackend.repository.card.CardRepository;
import com.luanphm.dictionarybackend.repository.card_set.CardSetRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import org.hibernate.Session;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardSetServiceImpl extends MyAbstractService<CardSet, String, CardSetDTO> implements CardSetService{

    @Autowired
    private CardSetRepository cardSetRepository;

    private CardSetMapping cardSetMapping = Mappers.getMapper(CardSetMapping.class);

    @Autowired
    private CardRepository cardRepository;

    @Override
    protected void inject() {
        this.repository = cardSetRepository;
        this.mappingHandler = Mappers.getMapper(CardSetMapping.class);
    }

    @Override
    public boolean add(CardSetDTO cardSetDto) {
        CardSet cardSet = mappingHandler.toEntity(cardSetDto);
        cardSet.setCreatedDate(CommonUtilities.getCurrentDateTime());

        return cardSetRepository.add(getSession(), cardSet);
    }

    @Override
    public boolean add(CardSetInsertDTO cardSetInsertDto) {
        CardSet cardSet = cardSetMapping.toCardSet(cardSetInsertDto);
        cardSet.setId(CommonUtilities.generateUniqueId());
        cardSet.setCreatedDate(CommonUtilities.getCurrentDateTime());

        for (Card card : cardSet.getCards()) {
            card.setId(CommonUtilities.generateUniqueId());
            card.setCardSet(cardSet);
        }
        Session session = getSession();

        return cardSetRepository.add(session, cardSet);
    }
}
