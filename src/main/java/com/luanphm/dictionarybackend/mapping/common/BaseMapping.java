package com.luanphm.dictionarybackend.mapping.common;

import java.util.List;

public interface BaseMapping<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDtos (List<E> entites);


}
