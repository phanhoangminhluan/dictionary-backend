package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.controller.SharedController.MyAbstractController;
import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

//@Controller
//@RequestMapping(value = "card")
public class CardController extends MyAbstractController<String, CardDTO> {

    @Autowired
    private CardService cardService;

    @Override
    protected void inject() {
        this.service = cardService;
    }
}
