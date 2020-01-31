package com.luanphm.dictionarybackend.controller.SharedController;

import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.service.SharedService.MyInterfaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

public abstract class MyAbstractController<ID, D> {

    protected MyInterfaceService<ID, D> service;

    @PostConstruct
    protected abstract void inject();

    @GetMapping
    public ResponseEntity<D> getAll() {
        List<D> dtos = service.getAll();
        if (dtos == null || dtos.size() == 0) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.NOT_FOUND, ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, dtos, HttpStatus.OK);

        }
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable("id") ID id) {
        D dto = service.getById(id);
        if (dto == null) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.NOT_FOUND, ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.WORK_SUCCESSFULLY, dto, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity add(@Valid @RequestBody D dto) {
        boolean isAdded = service.add(dto);
        if (!isAdded) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_FAIL, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.ADDED, dto, HttpStatus.CREATED);
        }
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody D dto) {
        boolean isUpdated = service.update(dto);

        if (!isUpdated) {
            return ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_FAIL, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
        } else {
            return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.UPDATED, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") ID id) {
        service.deleteById(id);
        return ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.DELETED, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED);
    }
}
