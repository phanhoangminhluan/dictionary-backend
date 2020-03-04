package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardIdDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardLearnDTO;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;

public interface StudiableCardService extends MyInterfaceService<StudiableCardId, StudiableCardDTO> {

    void deleteById(StudiableCardIdDTO studiableCardIdDTO);

    StudiableCardLearnDTO increaseRememberCount(StudiableCardIdDTO studiableCardIdDTO);

    StudiableCardLearnDTO increaseForgetCount(StudiableCardIdDTO studiableCardIdDTO);
}
