package com.luanphm.dictionarybackend.controller.SharedController;

import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class MyAbstractController<ID, D> {

    protected MyInterfaceService<ID, D> service;

    @PostConstruct
    protected abstract void inject();

    public ResponseEntity<D> getAll() throws Exception {
        List<D> dtos = service.getAll();
        if (dtos == null || dtos.size() == 0) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.NOT_FOUND, ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, dtos, HttpStatus.OK);

        }
    }

    public ResponseEntity getById(ID id) throws Exception {
        D dto = service.getById(id);
        if (dto == null) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.NOT_FOUND, ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, dto, HttpStatus.OK);
        }
    }

    public ResponseEntity add(D dto) throws Exception {
        boolean isAdded = service.add(dto);
        if (!isAdded) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.ADDED, dto, HttpStatus.CREATED);
        }
    }

    public ResponseEntity update(D dto) throws Exception {
        boolean isUpdated = service.update(dto);

        if (!isUpdated) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.UPDATED, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED);
        }
    }

    public ResponseEntity deleteById(ID id) throws Exception {
        D dto = service.deleteById(id);
        if (dto == null) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.DELETED, dto, HttpStatus.ACCEPTED);
        }
    }
}
