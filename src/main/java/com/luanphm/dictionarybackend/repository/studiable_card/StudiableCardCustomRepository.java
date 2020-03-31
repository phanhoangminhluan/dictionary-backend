package com.luanphm.dictionarybackend.repository.studiable_card;

import com.luanphm.dictionarybackend.entity.StudiableCard;

import java.util.List;

public interface StudiableCardCustomRepository{

    StudiableCard increaseRememberCount(StudiableCard studiableCard);
    StudiableCard increaseForgetCount(StudiableCard studiableCard);

    boolean addMany(List<StudiableCard> studiableCards);
}
