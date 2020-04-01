package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.dto.CardSetInsertDTO;
import com.luanphm.dictionarybackend.dto.CardSetUpdateNameDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;

import java.util.List;

public interface CardSetService extends MyInterfaceService<String, CardSetDTO> {

     CardSetDTO add(CardSetInsertDTO cardSetInsertDto) throws Exception;

     CardSetDTO updateName(CardSetUpdateNameDTO cardSetUpdateNameDTO) throws Exception;

     CardSet getByUsername(String cardSetId) throws Exception;

     void addManyCards(CardSet cardSet, List<Card> cards);

}
