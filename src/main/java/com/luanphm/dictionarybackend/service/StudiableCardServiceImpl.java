package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardIdDTO;
import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.mapping.StudiableCardIdMapping;
import com.luanphm.dictionarybackend.mapping.StudiableCardMapping;
import com.luanphm.dictionarybackend.repository.studiable_card.StudiableCardRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudiableCardServiceImpl extends MyAbstractService<StudiableCard, StudiableCardId, StudiableCardDTO> implements StudiableCardService {

    @Autowired
    private StudiableCardRepository studiableCardRepository;

    private StudiableCardIdMapping studiableCardIdMapping = Mappers.getMapper(StudiableCardIdMapping.class);

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
    public void increaseRememberCount(StudiableCardIdDTO studiableCardIdDTO) {
        StudiableCardId studiableCardId = studiableCardIdMapping.toEntity(studiableCardIdDTO);
        StudiableCard studiableCard = studiableCardRepository.getById(studiableCardId);
        studiableCardRepository.increaseRememberCount(studiableCard);
    }

    @Override
    public void increaseForgetCount(StudiableCardIdDTO studiableCardIdDTO) {
        StudiableCardId studiableCardId = studiableCardIdMapping.toEntity(studiableCardIdDTO);
        StudiableCard studiableCard = studiableCardRepository.getById(studiableCardId);
        studiableCardRepository.increaseForgetCount(studiableCard);
    }
}
