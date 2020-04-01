package com.luanphm.dictionarybackend.repository.studiable_card;

import com.luanphm.dictionarybackend.constant.ExceptionConstants;
import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractSession;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudiableCardCustomRepositoryImpl extends MyAbstractSession implements StudiableCardCustomRepository {

   @Override
   @Transactional
    public StudiableCard increaseRememberCount(StudiableCard studiableCard) throws Exception {

        Session session = getSession();
        studiableCard.setRemember(true);
        studiableCard.increaseRememberCount();
        try {
            session.update(studiableCard);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_UPDATE);
        }
       return studiableCard;
    }

    @Override
    @Transactional
    public StudiableCard increaseForgetCount(StudiableCard studiableCard) throws Exception {

        Session session = getSession();
        studiableCard.setRemember(false);
        studiableCard.increaseForgetCount();

        try {
            session.update(studiableCard);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_UPDATE);
        }
        return studiableCard;
    }

    @Override
    @Transactional
    public boolean addMany(List<StudiableCard> studiableCards) throws Exception {

           Session session = getSession();
           for (StudiableCard studiableCard : studiableCards) {
               try {
                   session.save(studiableCard);
               } catch (Exception e) {
                   e.printStackTrace();
                   throw new Exception(ExceptionConstants.ERROR_WHEN_UPDATE);
               }
           }
        return true;
    }

}
