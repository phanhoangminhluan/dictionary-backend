package com.luanphm.dictionarybackend.repository.card_set_session;

import com.luanphm.dictionarybackend.entity.CardSetSession;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CardSetSessionJpaRepository extends MyJpaRepository<CardSetSession, String> {

    CardSetSession findByCardSetId(String cardSetId);

    long deleteByCardSet_Id(String cardSetId);

}
