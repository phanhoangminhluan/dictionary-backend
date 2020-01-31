package com.luanphm.dictionarybackend.repository.card_set;

import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardSetJpaRepository extends MyJpaRepository<CardSet, String> {
}
