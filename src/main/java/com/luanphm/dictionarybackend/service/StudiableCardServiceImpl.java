package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.ExceptionConstants;
import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardIdDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardLearnDTO;
import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.mapping.StudiableCardIdMapping;
import com.luanphm.dictionarybackend.mapping.StudiableCardMapping;
import com.luanphm.dictionarybackend.repository.studiable_card.StudiableCardRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudiableCardServiceImpl extends MyAbstractService<StudiableCard, StudiableCardId, StudiableCardDTO> implements StudiableCardService {

    @Autowired
    private StudiableCardRepository studiableCardRepository;

    private StudiableCardIdMapping studiableCardIdMapping = Mappers.getMapper(StudiableCardIdMapping.class);

    private StudiableCardMapping studiableCardMapping = Mappers.getMapper(StudiableCardMapping.class);

    @Override
    protected void inject() {
        this.repository = studiableCardRepository;
        this.mappingHandler = Mappers.getMapper(StudiableCardMapping.class);
    }

    public void deleteById(StudiableCardIdDTO studiableCardIdDTO) throws Exception {
        StudiableCardId studiableCardId = studiableCardIdMapping.toEntity(studiableCardIdDTO);
        super.deleteById(studiableCardId);
    }

    @Override
    public StudiableCardLearnDTO increaseRememberCount(StudiableCardIdDTO studiableCardIdDTO) throws Exception {
        StudiableCard studiableCard = getStudiableCardById(studiableCardIdDTO);

        if (studiableCard == null) {
            throw new Exception(ExceptionConstants.NO_OBJECT_FOUND);
        }

        studiableCard = studiableCardRepository.increaseRememberCount(studiableCard);

        return studiableCardMapping.toLearnDto(studiableCard);
    }

    @Override
    public StudiableCardLearnDTO increaseForgetCount(StudiableCardIdDTO studiableCardIdDTO) throws Exception {

        StudiableCard studiableCard = getStudiableCardById(studiableCardIdDTO);

        if (studiableCard == null) return null;

        studiableCard = studiableCardRepository.increaseForgetCount(studiableCard);

        return studiableCardMapping.toLearnDto(studiableCard);
    }

    @Override
    public void deleteByCardSetSessionId(String cardSetSessionId) throws Exception {
        try {
            studiableCardRepository.deleteById_CardSetSession_Id(cardSetSessionId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_DELETE);
        }
    }

    @Override
    public void deleteByCardSetIdAndUserName(String cardSetId) throws Exception {
        String username = SecurityUtils.getCurrentUser();
        try {
            studiableCardRepository.deleteById_Card_CardSet_IdAndId_Card_CardSet_User_Id(cardSetId, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_DELETE);
        }
    }

    @Override
    public void addMany(List<StudiableCard> studiableCards) throws Exception {
        studiableCardRepository.addMany(studiableCards);
    }

    @Override
    public void deleteByCardId(String cardId) throws Exception {

        try {
            studiableCardRepository.deleteById_Card_Id(cardId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_DELETE);
        }
    }

    private StudiableCard getStudiableCardById(StudiableCardIdDTO studiableCardIdDTO) throws Exception {
        StudiableCardId studiableCardId = studiableCardIdMapping.toEntity(studiableCardIdDTO);

        String username = SecurityUtils.getCurrentUser();
        StudiableCard studiableCard = studiableCardRepository
                .getById_Card_IdAndId_Card_CardSet_User_Id(
                        studiableCardId.getCard().getId(),
                        username
                );
        if (studiableCard == null) throw new Exception(ExceptionConstants.NO_OBJECT_FOUND);
        return studiableCard;
    }

}
