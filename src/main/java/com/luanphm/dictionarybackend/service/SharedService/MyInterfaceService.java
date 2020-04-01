package com.luanphm.dictionarybackend.service.SharedService;

import java.util.List;

public interface MyInterfaceService<ID, D> {

    List<D> getAll() throws Exception;

    D getById(ID id) throws Exception;

    boolean add(D dto) throws Exception;

    boolean update(D dto) throws Exception;

    D deleteById(ID id) throws Exception;

}
