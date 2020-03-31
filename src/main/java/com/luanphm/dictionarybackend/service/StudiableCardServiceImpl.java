package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardIdDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardLearnDTO;
import com.luanphm.dictionarybackend.entity.*;
import com.luanphm.dictionarybackend.mapping.StudiableCardIdMapping;
import com.luanphm.dictionarybackend.mapping.StudiableCardMapping;
import com.luanphm.dictionarybackend.repository.card.CardRepository;
import com.luanphm.dictionarybackend.repository.card_set.CardSetRepository;
import com.luanphm.dictionarybackend.repository.card_set_session.CardSetSessionRepository;
import com.luanphm.dictionarybackend.repository.studiable_card.StudiableCardRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.hibernate.Session;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudiableCardServiceImpl extends MyAbstractService<StudiableCard, StudiableCardId, StudiableCardDTO> implements StudiableCardService {

    @Autowired
    private StudiableCardRepository studiableCardRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private CardSetSessionRepository cardSetSessionRepository;

    private StudiableCardIdMapping studiableCardIdMapping = Mappers.getMapper(StudiableCardIdMapping.class);

    private StudiableCardMapping studiableCardMapping = Mappers.getMapper(StudiableCardMapping.class);

    @Override
    protected void inject() {
        this.repository = studiableCardRepository;
        this.mappingHandler = Mappers.getMapper(StudiableCardMapping.class);
    }

    public void deleteById(StudiableCardIdDTO studiableCardIdDTO) {
        StudiableCardId studiableCardId = studiableCardIdMapping.toEntity(studiableCardIdDTO);
        super.deleteById(studiableCardId);
    }

    @Override
    public StudiableCardLearnDTO increaseRememberCount(StudiableCardIdDTO studiableCardIdDTO) {
        StudiableCard studiableCard = getStudiableCardById(studiableCardIdDTO);

        if (studiableCard == null) return null;

        studiableCard = studiableCardRepository.increaseRememberCount(studiableCard);

        return studiableCardMapping.toLearnDto(studiableCard);
    }

    @Override
    public StudiableCardLearnDTO increaseForgetCount(StudiableCardIdDTO studiableCardIdDTO) {

        StudiableCard studiableCard = getStudiableCardById(studiableCardIdDTO);

        if (studiableCard == null) return null;

        studiableCard = studiableCardRepository.increaseForgetCount(studiableCard);

        return studiableCardMapping.toLearnDto(studiableCard);
    }

    @Override
    public boolean addManyStudiableCard(CardSetSession cardSetSession, List<Card> cards) {
        String username = SecurityUtils.getCurrentUser();
        if (username == null) return false;

        if (cards == null || cards.size() == 0) return false;

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
        boolean isSuccess = studiableCardRepository.addMany(studiableCards);
        return isSuccess;
    }

    private StudiableCard getStudiableCardById(StudiableCardIdDTO studiableCardIdDTO) {
        StudiableCardId studiableCardId = studiableCardIdMapping.toEntity(studiableCardIdDTO);

        String username = SecurityUtils.getCurrentUser();
        StudiableCard studiableCard = studiableCardRepository
                .getById_Card_IdAndId_Card_CardSet_User_Id(
                        studiableCardId.getCard().getId(),
                        username
                );
        return studiableCard;
    }

}
