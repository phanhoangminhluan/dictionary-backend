package com.luanphm.dictionarybackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.service.WordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "word-detail")
public class WordDetailController {

    @Autowired
    private WordDetailService wordDetailService;

    @GetMapping(value = "word/{word}")
    public ResponseDTO getWord(@PathVariable String word) {
        WordDetailDTO wordDetailDTO = wordDetailService.getWord(word);

        return wordDetailDTO != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, wordDetailDTO)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND, ResponseDTO.EMPTY_BODY);
    }

//    @GetMapping(value = "term/{term}/value/{value}")
    public ResponseDTO getByTerm(@PathVariable String term, @PathVariable String value) {
        List<WordDetailDTO> dtos =  wordDetailService.getByTerm(term, value);

        return dtos != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, dtos)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND, ResponseDTO.EMPTY_BODY);
    }

    @PutMapping(value = "word")
    public ResponseDTO updateWord(@RequestBody WordDetailDTO wordDetailDTO) throws JsonProcessingException {
        boolean isSuccess = wordDetailService.update(wordDetailDTO);

        return isSuccess
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, ResponseDTO.EMPTY_BODY)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND,  ResponseDTO.EMPTY_BODY);
    }

    @DeleteMapping(value = "word/{word}")
    public ResponseDTO deleteWord(@PathVariable String word) {
        boolean isSuccess = wordDetailService.delete(word);

        return isSuccess
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, ResponseDTO.EMPTY_BODY)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND,  ResponseDTO.EMPTY_BODY);
    }
}