package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.dto.CardSetSessionLearningDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.entity.CardSetSession;
import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.mapping.CardSetSessionMapping;
import com.luanphm.dictionarybackend.mapping.StudiableCardMapping;
import com.luanphm.dictionarybackend.repository.card_set.CardSetRepository;
import com.luanphm.dictionarybackend.repository.card_set_session.CardSetSessionRepository;
import com.luanphm.dictionarybackend.repository.studiable_card.StudiableCardRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardSetSessionServiceImpl extends MyAbstractService<CardSetSession, String, CardSetSessionDTO> implements CardSetSessionService{

    @Autowired
    private CardSetSessionRepository cardSetSessionRepository;

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private StudiableCardRepository studiableCardRepository;


    private StudiableCardMapping studiableCardMapping = Mappers.getMapper(StudiableCardMapping.class);
    @Override
    protected void inject() {
        this.repository = cardSetSessionRepository;
        this.mappingHandler = Mappers.getMapper(CardSetSessionMapping.class);
    }

    @Override
    public CardSetSessionLearningDTO generateLearnSession(String cardSetId) {

        CardSetSession cardSetSession = getCardSetSessionByCardSetId(cardSetId);

        if (cardSetSession == null) {
            CardSet cardSet = cardSetRepository.getById(cardSetId);
            if (cardSet == null) return null;

            cardSetSession = cardSetSessionRepository.generateCardSetSession(cardSet);
            if (cardSetSession == null) return null;
        }
        List<StudiableCard> studiableCards = cardSetSession.getStudiableCards();
        if (studiableCards == null) return null;

        List<StudiableCardDTO> studiableCardDtos =  studiableCardMapping.toDtos(studiableCards);
        if (studiableCardDtos == null) return null;

        return CardSetSessionLearningDTO
                .builder()
                .cardSetId(cardSetId)
                .cardSetSessionId(cardSetSession.getId())
                .studiableCards(studiableCardDtos)
                .build();
    }

    @Override
    public CardSetSessionLearningDTO reset(String cardSetId) {

        CardSetSession cardSetSession = deleteByCardSetId(cardSetId);
        if(cardSetSession == null) return null;

        CardSetSessionLearningDTO cardSetSessionLearningDTO = generateLearnSession(cardSetId);

        return cardSetSessionLearningDTO;
    }

    public CardSetSession deleteByCardSetId(String cardSetId) {
        try {
            CardSetSession cardSetSession = cardSetSessionRepository.findByCardSetId(cardSetId);
            if (cardSetSession == null) return null;

            studiableCardRepository.deleteById_CardSetSession_Id(cardSetSession.getId());
            cardSetSessionRepository.deleteByCardSet_Id(cardSetId);

            return cardSetSession;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private CardSetSession getCardSetSessionByCardSetId(String cardSetId) {
        return cardSetSessionRepository.findByCardSetId(cardSetId);
    }
}
