package com.luanphm.dictionarybackend.repository.card_set_session;

import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.entity.CardSetSession;

public interface CardSetSessionCustomRepository {

    CardSetSession generateCardSetSession(CardSet cardSet) throws Exception;
}
