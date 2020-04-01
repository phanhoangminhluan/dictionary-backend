package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardIdDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardLearnDTO;
import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;

import java.util.List;

public interface StudiableCardService extends MyInterfaceService<StudiableCardId, StudiableCardDTO> {

    void deleteById(StudiableCardIdDTO studiableCardIdDTO) throws Exception;

    StudiableCardLearnDTO increaseRememberCount(StudiableCardIdDTO studiableCardIdDTO) throws Exception;

    StudiableCardLearnDTO increaseForgetCount(StudiableCardIdDTO studiableCardIdDTO) throws Exception;

    void deleteByCardSetSessionId(String cardSetSessionId) throws Exception;

    void deleteByCardSetIdAndUserName(String cardSetId) throws Exception;

    void addMany(List<StudiableCard> studiableCards) throws Exception;

    void deleteByCardId(String cardId) throws Exception;
}
