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
    public void increaseRememberCount(StudiableCard studiableCard) {
        Session session = getSession();
        studiableCard.setRemember(true);
        studiableCard.increaseRememberCount();
        session.update(studiableCard);

    }

    @Override
    @Transactional
    public void increaseForgetCount(StudiableCard studiableCard) {
        Session session = getSession();
        studiableCard.setRemember(false);
        studiableCard.increaseForgetCount();
        session.save(studiableCard);
    }

}
