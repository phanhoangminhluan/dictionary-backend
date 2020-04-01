package com.luanphm.dictionarybackend.repository.studiable_card;

import com.luanphm.dictionarybackend.entity.StudiableCard;

import java.util.List;

public interface StudiableCardCustomRepository{

    StudiableCard increaseRememberCount(StudiableCard studiableCard) throws Exception;
    StudiableCard increaseForgetCount(StudiableCard studiableCard) throws Exception;

    boolean addMany(List<StudiableCard> studiableCards) throws Exception;
}
