package com.luanphm.dictionarybackend.repository.card_set;

import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CardSetJpaRepository extends MyJpaRepository<CardSet, String> {

     List<CardSet> getByUser_Id(String userId);

     CardSet getByUser_IdAndId(String userId, String id);

}
