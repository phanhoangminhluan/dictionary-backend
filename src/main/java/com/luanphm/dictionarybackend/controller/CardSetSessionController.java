package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.controller.SharedController.MyAbstractController;
import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.service.CardSetSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "card-set-session")
public class CardSetSessionController extends MyAbstractController<String, CardSetSessionDTO> {

    @Autowired
    private CardSetSessionService cardSetSessionService;
    @Override
    protected void inject() {
        this.service = cardSetSessionService;
    }

    @Override
    public ResponseEntity add(@Valid CardSetSessionDTO dto) {
        return ResponseDTO.serviceUnavailable("Please use POST flashcard/learn/{cardSetId} to create card set session");
    }

    @Override
    public ResponseEntity update(CardSetSessionDTO dto) {
        return ResponseDTO.serviceUnavailable("Can not update a card set session");
    }
}
