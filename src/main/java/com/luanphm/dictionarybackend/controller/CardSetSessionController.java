package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.controller.SharedController.MyAbstractController;
import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.service.CardSetSessionService;
import org.springframework.beans.factory.annotation.Autowired;

//@Controller
//@RequestMapping(value = "card-set-session")
public class CardSetSessionController extends MyAbstractController<String, CardSetSessionDTO> {

    @Autowired
    private CardSetSessionService cardSetSessionService;
    @Override
    protected void inject() {
        this.service = cardSetSessionService;
    }
}
