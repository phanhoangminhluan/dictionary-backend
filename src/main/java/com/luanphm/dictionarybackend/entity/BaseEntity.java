package com.luanphm.dictionarybackend.entity;


import com.luanphm.dictionarybackend.handler.MappingHandler;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity<ID extends Serializable> {

    @Id
    protected ID id;

}
