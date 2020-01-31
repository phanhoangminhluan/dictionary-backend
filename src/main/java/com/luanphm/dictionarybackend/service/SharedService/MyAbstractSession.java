package com.luanphm.dictionarybackend.service.SharedService;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public abstract class MyAbstractSession {

    @Autowired
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

}
