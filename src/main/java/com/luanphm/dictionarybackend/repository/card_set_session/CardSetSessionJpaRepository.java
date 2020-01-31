package com.luanphm.dictionarybackend.repository.card_set_session;

import com.luanphm.dictionarybackend.entity.CardSetSession;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardSetSessionJpaRepository extends MyJpaRepository<CardSetSession, String> {
}
