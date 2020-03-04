package com.luanphm.dictionarybackend.repository.studiable_card;

import com.luanphm.dictionarybackend.entity.StudiableCard;

public interface StudiableCardCustomRepository{

    StudiableCard increaseRememberCount(StudiableCard studiableCard);
    StudiableCard increaseForgetCount(StudiableCard studiableCard);
}
