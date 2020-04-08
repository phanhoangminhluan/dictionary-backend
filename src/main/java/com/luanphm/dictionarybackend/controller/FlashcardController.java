package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.*;
import com.luanphm.dictionarybackend.service.CardSetSessionService;
import com.luanphm.dictionarybackend.service.StudiableCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "flashcard")
public class FlashcardController {

    @Autowired
    private CardSetSessionService cardSetSessionService;

    @Autowired
    private StudiableCardService studiableCardService;

    @PostMapping("learn/{cardSetId}")
    public ResponseEntity learn (@PathVariable String cardSetId) throws Exception {

        CardSetSessionLearningDTO cardSetSession = cardSetSessionService.generateLearnSession(cardSetId);
        return cardSetSession != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetSession, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping("remember/{cardId}")
    public ResponseEntity remember(@PathVariable String cardId) throws Exception {
        String cardSetSessionId = "";
        StudiableCardIdDTO studiableCardIdDTO = new StudiableCardIdDTO(cardId, cardSetSessionId);
        StudiableCardLearnDTO studiableCardDTO = studiableCardService.increaseRememberCount(studiableCardIdDTO);
        return studiableCardDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, studiableCardDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping("forget/{cardId}")
    public ResponseEntity forget( @PathVariable String cardId) throws Exception {
        String cardSetSessionId = "";
        StudiableCardIdDTO studiableCardIdDTO = new StudiableCardIdDTO(cardId, cardSetSessionId);
        StudiableCardLearnDTO studiableCardDTO = studiableCardService.increaseForgetCount(studiableCardIdDTO);
        return studiableCardDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, studiableCardDTO, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @GetMapping("reset-progress/{cardSetId}")
    public ResponseEntity resetProgress(@PathVariable String cardSetId) throws Exception {

        CardSetSessionLearningDTO cardSetSession = cardSetSessionService.reset(cardSetId);
        return cardSetSession != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, cardSetSession, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @GetMapping("count/{cardSetId}")
    public ResponseEntity countRememberAndForget(@PathVariable String cardSetId) throws Exception {
        StudiableCardCountDTO studiableCardCountDTO = cardSetSessionService.countRememberAndForget(cardSetId);
        return studiableCardCountDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, studiableCardCountDTO, HttpStatus.OK)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }
}
