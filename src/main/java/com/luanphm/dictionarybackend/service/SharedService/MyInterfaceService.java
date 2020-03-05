package com.luanphm.dictionarybackend.service.SharedService;

import java.util.List;

public interface MyInterfaceService<ID, D> {

    List<D> getAll();

    D getById(ID id);

    boolean add(D dto);

    boolean update(D dto);

    D deleteById(ID id);

}
