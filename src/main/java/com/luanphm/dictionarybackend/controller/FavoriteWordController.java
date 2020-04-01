package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.FavoriteWordDTO;
import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.service.FavoriteWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favorite-word")
public class FavoriteWordController {

    @Autowired
    private FavoriteWordService favoriteWordService;

    @GetMapping
    public ResponseEntity getWords() throws Exception {
        List<FavoriteWordDTO> words = favoriteWordService.getWords();
        return words != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, words, HttpStatus.OK)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PostMapping("{word}")
    public ResponseEntity addWord(@PathVariable String word) throws Exception {
        boolean isAdded = favoriteWordService.addWord(word);
        return isAdded
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @DeleteMapping("{word}")
    public ResponseEntity deleteWord(@PathVariable String word) throws Exception {
        boolean isDeleted = favoriteWordService.deleteWord(word);
        return isDeleted
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }


}
