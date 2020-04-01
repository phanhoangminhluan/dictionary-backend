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

import javax.validation.Valid;

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
    public ResponseEntity add(@Valid CardSetDTO dto) {
        return ResponseDTO.serviceUnavailable("Please use POST card-set/custom to create card set");
    }

    @Override
    public ResponseEntity update(CardSetDTO dto) {
        return ResponseDTO.serviceUnavailable("Please use PUT card-set/custom to update card set");
    }

    @PostMapping("custom")
    public ResponseEntity createCardSet(@RequestBody CardSetInsertDTO cardSetInsertDto) {

        CardSetDTO cardSetDTO = cardSetService.add(cardSetInsertDto);

        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping("custom")
    public ResponseEntity updateName(@RequestBody CardSetUpdateNameDTO cardSetUpdateNameDTO) {
        CardSetDTO cardSetDTO = cardSetService.updateName(cardSetUpdateNameDTO);
        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, cardSetDTO, HttpStatus.CONFLICT);

    }
}
