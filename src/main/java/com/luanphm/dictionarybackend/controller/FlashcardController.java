package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.*;
import com.luanphm.dictionarybackend.service.CardService;
import com.luanphm.dictionarybackend.service.CardSetService;
import com.luanphm.dictionarybackend.service.CardSetSessionService;
import com.luanphm.dictionarybackend.service.StudiableCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "flashcard")
public class FlashcardController {

    @Autowired
    private CardSetService cardSetService;

    @Autowired
    private CardService cardService;

    @Autowired
    private CardSetSessionService cardSetSessionService;

    @Autowired
    private StudiableCardService studiableCardService;

    @PostMapping
    public ResponseEntity createCardSet(@RequestBody CardSetInsertDTO cardSetInsertDto) {

        boolean isSuccess = cardSetService.add(cardSetInsertDto);

        return isSuccess
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_FAIL, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PostMapping("cards")
    public ResponseEntity addMannyCards(@RequestBody CardInsertManyDTO cards) {
        boolean isSuccess = cardService.addMany(cards);
        return isSuccess
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_FAIL, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @GetMapping("learn/{cardSetId}")
    public ResponseEntity learn (@PathVariable String cardSetId) {

        CardSetSessionLearningDTO cardSetSession = cardSetSessionService.generateLearnSession(cardSetId);
        return cardSetSession != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, cardSetSession, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_FAIL, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping("learn/remember")
    public ResponseEntity remember(@RequestBody StudiableCardIdDTO studiableCardIdDTO) {
        studiableCardService.increaseRememberCount(studiableCardIdDTO);
        return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED);
    }

    @PutMapping("learn/forget")
    public ResponseEntity forget(@RequestBody StudiableCardIdDTO studiableCardIdDTO) {
        studiableCardService.increaseForgetCount(studiableCardIdDTO);
        return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED);
    }

}
