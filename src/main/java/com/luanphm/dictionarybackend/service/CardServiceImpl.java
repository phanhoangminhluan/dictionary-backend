package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.dto.CardInsertManyDTO;
import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.mapping.CardMapping;
import com.luanphm.dictionarybackend.mapping.CardSetMapping;
import com.luanphm.dictionarybackend.repository.card.CardRepository;
import com.luanphm.dictionarybackend.repository.card_set.CardSetRepository;
import com.luanphm.dictionarybackend.repository.studiable_card.StudiableCardRepository;
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

    private CardSetMapping cardSetMapping = Mappers.getMapper(CardSetMapping.class);

    @Autowired
    private StudiableCardRepository studiableCardRepository;

    @Override
    protected void inject() {
        this.repository = cardRepository;
        this.mappingHandler = Mappers.getMapper(CardMapping.class);

    }

    @Override
    @Transactional
    public CardSetDTO addMany(CardInsertManyDTO dto) {

        String username = SecurityUtils.getCurrentUser();

        CardSet cardSet = cardSetRepository.getByUser_IdAndId(username, dto.getCardSetId());
        if (cardSet == null) return null;

        List<Card> cards = cardMapping.toCardsFromCardInsertDto(dto.getCards());

        for (Card card : cards) {
            card.setId(CommonUtilities.generateUniqueId());
            card.setCardSet(cardSet);
            cardSet.getCards().add(card);
        }
        boolean isUpdated = cardSetRepository.update(getSession(), cardSet);
        if (!isUpdated) return null;

        return cardSetMapping.toDto(cardSet);
    }

    private Card getCardById(String id) {
        Card card = repository.getById(id);
        if (card == null) return null;

        String username = SecurityUtils.getCurrentUser();
        String usernameOfCard = card.getCardSet().getUser().getId();
        if (!username.equals(usernameOfCard)) return null;

        return card;
    }

    @Override
    public CardDTO getById(String id) {

        String username = SecurityUtils.getCurrentUser();

        Card card = cardRepository.getByCardSet_User_IdAndId(username, id);
        if (card == null) return null;
        return cardMapping.toDto(card);
    }

    @Override
    public boolean update(CardDTO dto) {
        String username = SecurityUtils.getCurrentUser();
        Card card = cardRepository.getByCardSet_User_IdAndId(username, dto.getId());

        if (card == null);

        card.setTerm(dto.getTerm());
        card.setDefinition(dto.getDefinition());

        boolean isUpdated = repository.update(getSession(), card);
        return isUpdated;
    }

    @Override
    public List<CardDTO> getAll() {
        String username = SecurityUtils.getCurrentUser();
        List<Card> cards = cardRepository.getByCardSet_User_Id(username);

        if (cards == null || cards.size() == 0) return null;

        return cardMapping.toDtos(cards);
    }

    @Override
    public CardDTO deleteById(String id) {

        Card card = getCardById(id);
        if (card == null) return null;

        try {
            studiableCardRepository.deleteById_Card_Id(id);
            cardRepository.delete(card);
            return mappingHandler.toDto(card);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
