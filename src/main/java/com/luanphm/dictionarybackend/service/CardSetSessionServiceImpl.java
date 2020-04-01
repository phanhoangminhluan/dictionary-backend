package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.ExceptionConstants;
import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.dto.CardSetSessionLearningDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardCountDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.entity.*;
import com.luanphm.dictionarybackend.mapping.CardSetSessionMapping;
import com.luanphm.dictionarybackend.mapping.StudiableCardMapping;
import com.luanphm.dictionarybackend.repository.card_set_session.CardSetSessionRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardSetSessionServiceImpl extends MyAbstractService<CardSetSession, String, CardSetSessionDTO> implements CardSetSessionService{

    @Autowired
    private CardSetSessionRepository cardSetSessionRepository;

    @Autowired
    private CardSetService cardSetService;

    @Autowired
    private StudiableCardService studiableCardService;


    private StudiableCardMapping studiableCardMapping = Mappers.getMapper(StudiableCardMapping.class);
    @Override
    protected void inject() {
        this.repository = cardSetSessionRepository;
        this.mappingHandler = Mappers.getMapper(CardSetSessionMapping.class);
    }

    private CardSetSession  getByUsername(String cardSetSessionId) throws Exception {
        String username = SecurityUtils.getCurrentUser();
        CardSetSession cardSetSession = cardSetSessionRepository.getByCardSet_User_IdAndId(username, cardSetSessionId);
        if (cardSetSession == null) {
            throw new Exception(ExceptionConstants.NO_OBJECT_FOUND);
        }
        return cardSetSession;
    }

    @Override
    public CardSetSessionDTO getById(String id) throws Exception {
        CardSetSession cardSetSession = getByUsername(id);
        return mappingHandler.toDto(cardSetSession);
    }

    @Override
    public List<CardSetSessionDTO> getAll() throws Exception {
        String username = SecurityUtils.getCurrentUser();
        List<CardSetSession> cardSetSessions = cardSetSessionRepository.getByCardSet_User_Id(username);
        if (cardSetSessions == null || cardSetSessions.size() == 0)  {
            throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);
        }
        return mappingHandler.toDtos(cardSetSessions);
    }


    @Override
    public CardSetSessionDTO deleteById(String id) throws Exception {
        CardSetSession cardSetSession = getByUsername(id);
        try {
            cardSetSessionRepository.delete(cardSetSession);
            return mappingHandler.toDto(cardSetSession);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_DELETE);
        }
    }

    @Override
    public CardSetSessionLearningDTO generateLearnSession(String cardSetId) throws Exception {
        String username = SecurityUtils.getCurrentUser();
        CardSetSession cardSetSession = cardSetSessionRepository.getByCardSet_User_IdAndCardSet_Id(username, cardSetId);

        if (cardSetSession == null) {
            CardSet cardSet = cardSetService.getByUsername(cardSetId);
            cardSetSession = cardSetSessionRepository.generateCardSetSession(cardSet);
        }
        List<StudiableCard> studiableCards = cardSetSession.getStudiableCards();
        if (studiableCards == null) {
            throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);
        }

        List<StudiableCardDTO> studiableCardDtos = studiableCardMapping.toDtos(studiableCards);
        if (studiableCardDtos == null) {
            throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);
        }

        return CardSetSessionLearningDTO
                .builder()
                .cardSetId(cardSetId)
                .cardSetSessionId(cardSetSession.getId())
                .studiableCards(studiableCardDtos)
                .build();
    }

    @Override
    public CardSetSessionLearningDTO reset(String cardSetId) throws Exception {

        getByCardSetIdAndUsername(cardSetId);

        try {

            deleteByCardSetId(cardSetId);

        } catch (Exception e) {

        }

        return generateLearnSession(cardSetId);
    }

    public CardSetSession deleteByCardSetId(String cardSetId) throws Exception {
        CardSetSession cardSetSession = null;
        try {
            cardSetSession = getByCardSetIdAndUsername(cardSetId);

        } catch (Exception e) {
            return null;
        }
        try {

            studiableCardService.deleteByCardSetSessionId(cardSetSession.getId());
            cardSetSessionRepository.deleteByCardSet_Id(cardSetId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_DELETE);
        }
        return cardSetSession;
    }

    @Override
    public CardSetSession getByCardSetIdAndUsername(String cardSetId) throws Exception {
        String username = SecurityUtils.getCurrentUser();
        CardSetSession cardSetSession = cardSetSessionRepository.getByCardSet_User_IdAndCardSet_Id(username, cardSetId);
        if (cardSetSession == null) throw new Exception(ExceptionConstants.NO_OBJECT_FOUND);
        return cardSetSession;
    }

    @Override
    public void addManyStudiableCards(String cardSetId, List<Card> cards) throws Exception {
        CardSetSession cardSetSession = getByCardSetIdAndUsername(cardSetId);

        List<StudiableCard> studiableCards = new ArrayList<>();
        for (Card card : cards) {
            StudiableCard studiableCard = StudiableCard.builder()
                    .id(StudiableCardId
                            .builder()
                            .card(card)
                            .cardSetSession(cardSetSession)
                            .build()
                    )
                    .build();
            studiableCards.add(studiableCard);

        }
        studiableCardService.addMany(studiableCards);
    }

    public StudiableCardCountDTO countRememberAndForget(String cardSetId) throws Exception {
        CardSetSession cardSetSession = getByCardSetIdAndUsername(cardSetId);

        int numberOfRememberWord = 0;
        int numberOfForgetWord = 0;
        List<StudiableCard> studiableCards = cardSetSession.getStudiableCards();

        if (studiableCards == null || studiableCards.size() == 0) {
            throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);
        }

        for (StudiableCard studiableCard : cardSetSession.getStudiableCards()) {
            if (studiableCard.isRemember()) {
                numberOfRememberWord++;
            } else {
                numberOfForgetWord++;
            }
        }
        return StudiableCardCountDTO.builder().rememberCount(numberOfRememberWord).forgetCount(numberOfForgetWord).build();
    }


}
