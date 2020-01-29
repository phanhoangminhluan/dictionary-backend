package com.luanphm.dictionarybackend.entity;


import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Setter
public abstract class IdObject<ID extends Serializable> {

    @Id
    protected ID id;

    public ID getId() {
        return id;
    }
}
