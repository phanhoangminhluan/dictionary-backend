package com.luanphm.dictionarybackend.service.SharedService;

import com.luanphm.dictionarybackend.entity.BaseEntity;
import com.luanphm.dictionarybackend.handler.MappingHandler;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;
import org.mapstruct.factory.Mappers;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class MyAbstractService<E extends BaseEntity, ID extends Serializable, D> extends MyAbstractSession implements MyInterfaceService<ID, D> {

    protected MyJpaRepository<E, ID> repository;

    protected BaseMapping<E, D> mappingHandler;

    @PostConstruct
    protected abstract void inject();

    public List<D> getAll() {

        List<E> entites = repository.getAll();

        List<D> dtos = mappingHandler.toDtos(entites);

        return dtos;
    }

    public D getById(ID id) {
        E entity = repository.getById(id);
        D dto = mappingHandler.toDto(entity);
        return dto;
    }

    public boolean add(D dto) {
        boolean isSuccess = false;
        E entity = mappingHandler.toEntity(dto);
        try {
            isSuccess = repository.add(getSession(), entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean update(D dto) {
        boolean isSuccess = false;
        E entity = mappingHandler.toEntity(dto);
        try {
            isSuccess = repository.update(getSession(), entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isSuccess;
    }

    public void deleteById(ID id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
