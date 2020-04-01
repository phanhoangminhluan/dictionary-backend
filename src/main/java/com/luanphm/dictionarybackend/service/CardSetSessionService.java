package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.dto.CardSetSessionLearningDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardCountDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSetSession;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;

import java.util.List;

public interface CardSetSessionService extends MyInterfaceService<String, CardSetSessionDTO> {

    CardSetSessionLearningDTO generateLearnSession(String cardSetId) throws Exception;

    CardSetSessionLearningDTO reset(String cardSetId) throws Exception;

    StudiableCardCountDTO countRememberAndForget(String cardSetId) throws Exception;

    CardSetSession deleteByCardSetId(String cardSetId) throws Exception;

    CardSetSession getByCardSetIdAndUsername(String cardSetId) throws Exception;

    void addManyStudiableCards(String cardSetId, List<Card> cards) throws Exception;

}
