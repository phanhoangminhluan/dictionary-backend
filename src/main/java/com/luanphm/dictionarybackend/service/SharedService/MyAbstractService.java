package com.luanphm.dictionarybackend.service.SharedService;

import com.luanphm.dictionarybackend.handler.MyJpaRepository;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

public abstract class MyAbstractService<E, ID extends Serializable, D> extends MyAbstractSession implements MyInterfaceService<ID, D> {

    protected MyJpaRepository<E, ID> repository;

    protected BaseMapping<E, D> mappingHandler;

    @PostConstruct
    protected abstract void inject();

    public List<D> getAll() {

        List<E> entites = repository.getAll();

        return  mappingHandler.toDtos(entites);
}

    public D getById(ID id) {
        E entity = repository.getById(id);
        return mappingHandler.toDto(entity);
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
        E entity = mappingHandler.toEntity(dto);
        try {
            return repository.update(getSession(), entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public D deleteById(ID id) {
        try {
            E entity = repository.getById(id);
            if (entity == null) return null;
            repository.deleteById(id);
            return mappingHandler.toDto(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
