package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.dto.CardInsertManyDTO;
import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;

public interface CardService extends MyInterfaceService<String, CardDTO> {


    CardSetDTO addMany(CardInsertManyDTO dto) throws Exception;

}
