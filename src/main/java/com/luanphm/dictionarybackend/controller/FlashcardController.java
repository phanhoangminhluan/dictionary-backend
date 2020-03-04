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

    @GetMapping("card-set/{cardSetId}")
    public ResponseEntity getById(@PathVariable String cardSetId) {

        CardSetDTO cardSetDTO = cardSetService.getById(cardSetId);

        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity createCardSet(@RequestBody CardSetInsertDTO cardSetInsertDto) {

        CardSetDTO cardSetDTO = cardSetService.add(cardSetInsertDto);

        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PostMapping("cards")
    public ResponseEntity addMannyCards(@RequestBody CardInsertManyDTO cards) {
        CardSetDTO cardSetDTO = cardService.addMany(cards);
        return cardSetDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @GetMapping("learn/{cardSetId}")
    public ResponseEntity learn (@PathVariable String cardSetId) {

        CardSetSessionLearningDTO cardSetSession = cardSetSessionService.generateLearnSession(cardSetId);
        return cardSetSession != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetSession, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @GetMapping("reset-progress/{cardSetId}")
    public ResponseEntity resetProgress(@PathVariable String cardSetId) {

        CardSetSessionLearningDTO cardSetSession = cardSetSessionService.reset(cardSetId);
        return cardSetSession != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetSession, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping("learn/remember/{cardSetSessionId}/{cardId}")
    public ResponseEntity remember(@PathVariable String cardSetSessionId, @PathVariable String cardId) {
        StudiableCardIdDTO studiableCardIdDTO = new StudiableCardIdDTO(cardId, cardSetSessionId);
        StudiableCardLearnDTO studiableCardDTO = studiableCardService.increaseRememberCount(studiableCardIdDTO);
        return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, studiableCardDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("learn/forget/{cardSetSessionId}/{cardId}")
    public ResponseEntity forget(@PathVariable String cardSetSessionId, @PathVariable String cardId) {
        StudiableCardIdDTO studiableCardIdDTO = new StudiableCardIdDTO(cardId, cardSetSessionId);
        StudiableCardLearnDTO studiableCardDTO = studiableCardService.increaseForgetCount(studiableCardIdDTO);
        return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, studiableCardDTO, HttpStatus.ACCEPTED);
    }
}
