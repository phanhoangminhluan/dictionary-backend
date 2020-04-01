package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.ExceptionConstants;
import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.dto.CardSetInsertDTO;
import com.luanphm.dictionarybackend.dto.CardSetUpdateNameDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.entity.User;
import com.luanphm.dictionarybackend.mapping.CardSetMapping;
import com.luanphm.dictionarybackend.repository.card_set.CardSetRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import org.hibernate.Session;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardSetServiceImpl extends MyAbstractService<CardSet, String, CardSetDTO> implements CardSetService{

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private CardSetSessionService cardSetSessionService;

    @Autowired
    private StudiableCardService studiableCardService;

    private CardSetMapping cardSetMapping = Mappers.getMapper(CardSetMapping.class);


    @Override
    protected void inject() {
        this.repository = cardSetRepository;
        this.mappingHandler = Mappers.getMapper(CardSetMapping.class);
    }

    public CardSet getByUsername(String cardSetId) throws Exception {
        String username = SecurityUtils.getCurrentUser();
        CardSet cardSet = cardSetRepository.getByUser_IdAndId(username, cardSetId);
        if (cardSet == null) {
            throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);
        }
        return cardSet;
    }

    @Override
    public List<CardSetDTO> getAll() throws Exception {

        String username = SecurityUtils.getCurrentUser();
        List<CardSet> cardSets = cardSetRepository.getByUser_Id(username);
        if (cardSets == null || cardSets.size() == 0) {
            throw new Exception(ExceptionConstants.NO_OBJECTS_FOUND);
        }
        return cardSetMapping.toDtos(cardSets);
    }

    @Override
    public CardSetDTO getById(String id) throws Exception {
        CardSet cardSet = getByUsername(id);
        return cardSetMapping.toDto(cardSet);
    }

    @Override
    public boolean add(CardSetDTO cardSetDto) throws Exception {
        CardSet cardSet = mappingHandler.toEntity(cardSetDto);
        cardSet.setCreatedDate(CommonUtilities.getCurrentDateTime());

        cardSetRepository.add(getSession(), cardSet);
        return true;
    }

    @Override
    public CardSetDTO add(CardSetInsertDTO cardSetInsertDto) throws Exception {
        String username = SecurityUtils.getCurrentUser();

        CardSet cardSet = cardSetMapping.toCardSet(cardSetInsertDto);
        cardSet.setId(CommonUtilities.generateUniqueId());
        cardSet.setCreatedDate(CommonUtilities.getCurrentDateTime());
        cardSet.setUser(User.builder().id(username).build());

        for (Card card : cardSet.getCards()) {
            card.setId(CommonUtilities.generateUniqueId());
            card.setCardSet(cardSet);
        }
        Session session = getSession();

        cardSetRepository.add(session, cardSet);
        return cardSetMapping.toDto(cardSet);
    }

    @Override
    public CardSetDTO updateName(CardSetUpdateNameDTO cardSetUpdateNameDTO) throws Exception {
        CardSet cardSet = getByUsername(cardSetUpdateNameDTO.getId());

        cardSet.setName(cardSetUpdateNameDTO.getName());
        cardSetRepository.update(getSession(), cardSet);

        return cardSetMapping.toDto(cardSet);
    }

    @Override
    public void addManyCards(CardSet cardSet, List<Card> cards) {
        for (Card card : cards) {
            card.setId(CommonUtilities.generateUniqueId());
            card.setCardSet(cardSet);
            cardSet.getCards().add(card);
        }
        cardSetRepository.save(cardSet);
    }

    @Override
    public CardSetDTO deleteById(String cardSetId) throws Exception {
        CardSet cardSet = getByUsername(cardSetId);

        try {
            studiableCardService.deleteByCardSetIdAndUserName(cardSetId);
            cardSetSessionService.deleteByCardSetId(cardSetId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            cardSetRepository.delete(cardSet);
            return mappingHandler.toDto(cardSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_DELETE);
        }
    }
}
