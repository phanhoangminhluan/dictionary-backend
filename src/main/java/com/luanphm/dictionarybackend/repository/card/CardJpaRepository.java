package com.luanphm.dictionarybackend.repository.card;

import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardJpaRepository extends MyJpaRepository<Card, String> {

}
