package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.controller.SharedController.MyAbstractController;
import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardIdDTO;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.service.StudiableCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "studiable-card")
public class StudiableCardController extends MyAbstractController<StudiableCardId, StudiableCardDTO> {

    @Autowired
    private StudiableCardService studiableCardService;

    @Override
    protected void inject() {
        this.service = studiableCardService;
    }
    @DeleteMapping("")
    public ResponseEntity deleteById(@RequestBody StudiableCardIdDTO studiableCardIdDTO) {
        studiableCardService.deleteById(studiableCardIdDTO);
        return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.DELETED, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED);
    }
    @Override
    public ResponseEntity deleteById(StudiableCardId studiableCardId) {
        return ResponseDTO.generateResponseObject(false, "Can not delete by this endpoint", null, HttpStatus.BAD_REQUEST);
    }
}
