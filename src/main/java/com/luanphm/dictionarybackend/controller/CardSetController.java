package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.controller.SharedController.MyAbstractController;
import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "card-set")
public class CardSetController extends MyAbstractController<String, CardSetDTO> {
    
    @Autowired
    private CardSetService cardSetService;

    @Override
    protected void inject() {
        this.service = cardSetService;

    }
}
