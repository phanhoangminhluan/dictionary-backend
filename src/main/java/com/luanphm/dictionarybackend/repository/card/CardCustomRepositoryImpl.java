package com.luanphm.dictionarybackend.repository.card;

import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractSession;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CardCustomRepositoryImpl extends MyAbstractSession implements CardCustomRepository{

    @Override
    @Transactional
    public boolean addMany(List<Card> cards) {
        Session session = getSession();
        for (Card card : cards) {
            try {
                session.save(card);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        return true;
    }
}
