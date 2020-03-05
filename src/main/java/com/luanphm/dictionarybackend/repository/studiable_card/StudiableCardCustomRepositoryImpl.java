package com.luanphm.dictionarybackend.repository.studiable_card;

import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractSession;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class StudiableCardCustomRepositoryImpl extends MyAbstractSession implements StudiableCardCustomRepository {
       @Override
       @Transactional
    public StudiableCard increaseRememberCount(StudiableCard studiableCard) {

        if (studiableCard == null) return null;

        Session session = getSession();
        studiableCard.setRemember(true);
        studiableCard.increaseRememberCount();
        try {
            session.update(studiableCard);
            return studiableCard;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public StudiableCard increaseForgetCount(StudiableCard studiableCard) {

        if (studiableCard == null) return null;

        Session session = getSession();
        studiableCard.setRemember(false);
        studiableCard.increaseForgetCount();

        try {
            session.update(studiableCard);
            return studiableCard;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
