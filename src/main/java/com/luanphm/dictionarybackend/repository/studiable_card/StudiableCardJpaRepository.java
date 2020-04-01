package com.luanphm.dictionarybackend.repository.studiable_card;

import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface StudiableCardJpaRepository extends MyJpaRepository<StudiableCard, StudiableCardId> {

    long deleteById_CardSetSession_Id(String cardSessionId);

    StudiableCard getById_Card_IdAndId_Card_CardSet_User_Id(String cardId, String userId);

    long deleteById_Card_Id(String cardId);

    long deleteById_Card_CardSet_IdAndId_Card_CardSet_User_Id(String cardSetId, String username);

}
