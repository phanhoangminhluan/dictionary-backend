package com.luanphm.dictionarybackend.repository.card;

import com.luanphm.dictionarybackend.entity.Card;

import java.util.List;

public interface CardCustomRepository {

    boolean addMany(List<Card> cards);

}
