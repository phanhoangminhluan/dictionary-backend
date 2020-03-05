package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.controller.SharedController.MyAbstractController;
import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.dto.CardInsertManyDTO;
import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "card")
public class CardController extends MyAbstractController<String, CardDTO> {

    @Autowired
    private CardService cardService;

    @Override
    protected void inject() {
        this.service = cardService;
    }

    @PostMapping("cards")
    public ResponseEntity addMannyCards(@RequestBody CardInsertManyDTO cards) {
        CardSetDTO cardSetDTO = cardService.addMany(cards);
        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }
}
