package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.ExceptionConstants;
import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.dto.CardInsertManyDTO;
import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.mapping.CardMapping;
import com.luanphm.dictionarybackend.mapping.CardSetMapping;
import com.luanphm.dictionarybackend.repository.card.CardRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl extends MyAbstractService<Card, String, CardDTO> implements CardService{

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardSetService cardSetService;

    private CardMapping cardMapping = Mappers.getMapper(CardMapping.class);

    private CardSetMapping cardSetMapping = Mappers.getMapper(CardSetMapping.class);

    @Autowired
    private StudiableCardService studiableCardService;

    @Autowired
    private CardSetSessionService cardSetSessionService;

    @Override
    protected void inject() {
        this.repository = cardRepository;
        this.mappingHandler = Mappers.getMapper(CardMapping.class);

    }

    @Override
    public CardSetDTO addMany(CardInsertManyDTO dto) throws Exception {

        CardSet cardSet = cardSetService.getByUsername(dto.getCardSetId());

        List<Card> cards = cardMapping.toCardsFromCardInsertDto(dto.getCards());

        cardSetService.addManyCards(cardSet, cards);

        try {
            cardSetSessionService.addManyStudiableCards(cardSet.getId(), cards);
        } catch (Exception e) {

        }

        return cardSetMapping.toDto(cardSet);
    }

    private Card getCardById(String id) throws Exception {
        String username = SecurityUtils.getCurrentUser();

        Card card = cardRepository.getByCardSet_User_IdAndId(username, id);
        if (card == null) throw new Exception(ExceptionConstants.NO_OBJECT_FOUND);
        return card;
    }

    @Override
    public CardDTO getById(String id) throws Exception {

        Card card = getCardById(id);

        return cardMapping.toDto(card);
    }

    @Override
    public boolean update(CardDTO dto) throws Exception {

        Card card = getCardById(dto.getId());
        card.setTerm(dto.getTerm());
        card.setDefinition(dto.getDefinition());

        cardRepository.update(getSession(), card);

        return true;
    }


    private List<Card> getAllByUsername() throws Exception {
        String username = SecurityUtils.getCurrentUser();
        List<Card> cards = cardRepository.getByCardSet_User_Id(username);
        if (cards == null || cards.size() == 0) throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);
        return cards;
    }

    @Override
    public List<CardDTO> getAll() throws Exception {
        List<Card> cards = getAllByUsername();
        return cardMapping.toDtos(cards);
    }

    @Override
    public CardDTO deleteById(String id) throws Exception {

        Card card = getCardById(id);
        try {
            studiableCardService.deleteByCardId(id);
        } catch (Exception e) {

        }
        try {
            cardRepository.delete(card);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_DELETE);
        }
        return mappingHandler.toDto(card);
    }


}
