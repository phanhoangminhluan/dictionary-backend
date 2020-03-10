package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.dto.CardSetSessionLearningDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardCountDTO;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;

public interface CardSetSessionService extends MyInterfaceService<String, CardSetSessionDTO> {

    CardSetSessionLearningDTO generateLearnSession(String cardSetId);

    CardSetSessionLearningDTO reset(String cardSetId);

    StudiableCardCountDTO countRememberAndForget(String cardSetId);


}
