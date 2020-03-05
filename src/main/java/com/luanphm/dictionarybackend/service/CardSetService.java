package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.dto.CardSetInsertDTO;
import com.luanphm.dictionarybackend.dto.CardSetUpdateNameDTO;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;

public interface CardSetService extends MyInterfaceService<String, CardSetDTO> {

     CardSetDTO add(CardSetInsertDTO cardSetInsertDto);

     CardSetDTO updateName(CardSetUpdateNameDTO cardSetUpdateNameDTO);

}
