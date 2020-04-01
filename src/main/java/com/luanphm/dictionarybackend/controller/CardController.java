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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "card")
public class CardController extends MyAbstractController<String, CardDTO> {

    @Autowired
    private CardService cardService;

    @Override
    protected void inject() {
        this.service = cardService;
    }

    @Override
    @GetMapping
    public ResponseEntity<CardDTO> getAll() throws Exception {
        return super.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {
        return super.getById(id);
    }

    @Override
    @PutMapping
    public ResponseEntity update(@RequestBody CardDTO dto) throws Exception {
        return super.update(dto);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable String id) throws Exception {
        return super.deleteById(id);
    }

    @PostMapping("cards")
    public ResponseEntity addMannyCards(@RequestBody CardInsertManyDTO cards) throws Exception {
        CardSetDTO cardSetDTO = cardService.addMany(cards);
        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }
}
