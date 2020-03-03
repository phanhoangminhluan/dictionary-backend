package com.luanphm.dictionarybackend.repository.studiable_card;

import com.luanphm.dictionarybackend.entity.StudiableCard;

public interface StudiableCardCustomRepository{

    void increaseRememberCount(StudiableCard studiableCard);
    void increaseForgetCount(StudiableCard studiableCard);
}
