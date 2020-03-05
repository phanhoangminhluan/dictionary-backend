package com.luanphm.dictionarybackend.service;

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

    private CardSetMapping cardSetMapping = Mappers.getMapper(CardSetMapping.class);


    @Override
    protected void inject() {
        this.repository = cardSetRepository;
        this.mappingHandler = Mappers.getMapper(CardSetMapping.class);
    }

    @Override
    public List<CardSetDTO> getAll() {
        String username = SecurityUtils.getCurrentUser();
        List<CardSet> cardSets = cardSetRepository.getByUser_Id(username);
        if (cardSets == null || cardSets.size() == 0) return null;
        return cardSetMapping.toDtos(cardSets);
    }

    @Override
    public CardSetDTO getById(String id) {
        String username = SecurityUtils.getCurrentUser();
        CardSet cardSet = cardSetRepository.getByUser_IdAndId(username, id);
        if (cardSet == null) return null;
        return cardSetMapping.toDto(cardSet);
    }

    @Override
    public boolean add(CardSetDTO cardSetDto) {
        CardSet cardSet = mappingHandler.toEntity(cardSetDto);
        cardSet.setCreatedDate(CommonUtilities.getCurrentDateTime());

        return cardSetRepository.add(getSession(), cardSet);
    }

    @Override
    public CardSetDTO add(CardSetInsertDTO cardSetInsertDto) {
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

        boolean isSuccess = cardSetRepository.add(session, cardSet);

        if (isSuccess) {
            return cardSetMapping.toDto(cardSet);
        }
        return null;
    }

    @Override
    public CardSetDTO updateName(CardSetUpdateNameDTO cardSetUpdateNameDTO) {
        String username = SecurityUtils.getCurrentUser();
        CardSet cardSet = cardSetRepository.getByUser_IdAndId(username, cardSetUpdateNameDTO.getId());
        if (cardSet == null) return null;

        cardSet.setName(cardSetUpdateNameDTO.getName());
        cardSetRepository.update(getSession(), cardSet);

        return cardSetMapping.toDto(cardSet);
    }

    @Override
    public CardSetDTO deleteById(String id) {
        String username = SecurityUtils.getCurrentUser();
        CardSet cardSet = cardSetRepository.getByUser_IdAndId(username, id);
        if (cardSet == null) return null;
        try {
            repository.delete(cardSet);
            return mappingHandler.toDto(cardSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
