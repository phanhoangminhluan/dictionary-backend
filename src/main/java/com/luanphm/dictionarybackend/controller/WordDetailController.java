package com.luanphm.dictionarybackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.service.WordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "word")
public class WordDetailController {

    @Autowired
    private WordDetailService wordDetailService;

    @GetMapping(value = "{word}")
    public ResponseEntity getWord(@PathVariable String word) {
        WordDetailDTO wordDetailDTO = wordDetailService.getWord(word);

        return wordDetailDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, wordDetailDTO, HttpStatus.OK)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND, ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
    }

//    @GetMapping(value = "term/{term}/value/{value}")
    public ResponseEntity getByTerm(@PathVariable String term, @PathVariable String value) {
        List<WordDetailDTO> dtos =  wordDetailService.getByTerm(term, value);

        return dtos != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, dtos, HttpStatus.OK)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND, ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "")
    public ResponseEntity createWord(@RequestBody WordDetailDTO wordDetailDTO) {
        boolean isSuccess = wordDetailService.create(wordDetailDTO);

        return isSuccess
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CREATED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_IS_EXISTED,  ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity updateWord(@RequestBody WordDetailDTO wordDetailDTO) throws JsonProcessingException {
        boolean isSuccess = wordDetailService.update(wordDetailDTO);

        return isSuccess
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND,  ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "{word}")
    public ResponseEntity deleteWord(@PathVariable String word) {
        boolean isSuccess = wordDetailService.delete(word);

        return isSuccess
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.OK)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND,  ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
    }
}