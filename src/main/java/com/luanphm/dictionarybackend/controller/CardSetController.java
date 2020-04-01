package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.controller.SharedController.MyAbstractController;
import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.dto.CardSetInsertDTO;
import com.luanphm.dictionarybackend.dto.CardSetUpdateNameDTO;
import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "card-set")
public class CardSetController extends MyAbstractController<String, CardSetDTO> {
    
    @Autowired
    private CardSetService cardSetService;

    @Override
    protected void inject() {
        this.service = cardSetService;

    }
    @Override
    @GetMapping
    public ResponseEntity<CardSetDTO> getAll() throws Exception {
        return super.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {
        return super.getById(id);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable String id) throws Exception {
        return super.deleteById(id);
    }

    @PostMapping
    public ResponseEntity createCardSet(@RequestBody CardSetInsertDTO cardSetInsertDto) throws Exception {

        CardSetDTO cardSetDTO = cardSetService.add(cardSetInsertDto);

        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity updateName(@RequestBody CardSetUpdateNameDTO cardSetUpdateNameDTO) throws Exception {
        CardSetDTO cardSetDTO = cardSetService.updateName(cardSetUpdateNameDTO);
        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, cardSetDTO, HttpStatus.CONFLICT);

    }
}
