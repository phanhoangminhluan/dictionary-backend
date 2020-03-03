package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.dto.CardInsertManyDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.mapping.CardMapping;
import com.luanphm.dictionarybackend.repository.card.CardRepository;
import com.luanphm.dictionarybackend.repository.card_set.CardSetRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CardServiceImpl extends MyAbstractService<Card, String, CardDTO> implements CardService{

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardSetRepository cardSetRepository;

    private CardMapping cardMapping = Mappers.getMapper(CardMapping.class);

    @Override
    protected void inject() {
        this.repository = cardRepository;
        this.mappingHandler = Mappers.getMapper(CardMapping.class);

    }

    @Override
    @Transactional
    public boolean addMany(CardInsertManyDTO dto) {

        CardSet cardSet = cardSetRepository.getById(dto.getCardSetId());

        List<Card> cards = cardMapping.toCardsFromCardInsertDto(dto.getCards());

        for (Card card : cards) {
            card.setId(CommonUtilities.generateUniqueId());
            card.setCardSet(cardSet);
        }

        return cardRepository.addMany(cards);
    }
}