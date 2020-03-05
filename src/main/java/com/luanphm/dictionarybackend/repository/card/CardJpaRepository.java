package com.luanphm.dictionarybackend.repository.card;

import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardJpaRepository extends MyJpaRepository<Card, String> {

    List<Card> getByCardSet_User_Id(String userId);

    Card getByCardSet_User_IdAndId(String userId, String id);


}
