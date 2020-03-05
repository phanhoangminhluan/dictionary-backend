package com.luanphm.dictionarybackend.repository.card_set_session;

import com.luanphm.dictionarybackend.entity.CardSetSession;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CardSetSessionJpaRepository extends MyJpaRepository<CardSetSession, String> {

    CardSetSession getByCardSet_User_IdAndId(String userId, String cardSetSessionId);

    CardSetSession getByCardSet_User_IdAndCardSet_Id(String userId, String cardSetId);

    List<CardSetSession> getByCardSet_User_Id(String userId);

    long deleteByCardSet_Id(String cardSetId);

}
