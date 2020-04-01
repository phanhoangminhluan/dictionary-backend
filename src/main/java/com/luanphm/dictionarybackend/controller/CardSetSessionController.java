package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.controller.SharedController.MyAbstractController;
import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.service.CardSetSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "card-set-session")
public class CardSetSessionController extends MyAbstractController<String, CardSetSessionDTO> {

    @Autowired
    private CardSetSessionService cardSetSessionService;
    @Override
    protected void inject() {
        this.service = cardSetSessionService;
    }

    @Override
    @GetMapping
    public ResponseEntity<CardSetSessionDTO> getAll() throws Exception {
        return super.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {
        return super.getById(id);
    }
}
