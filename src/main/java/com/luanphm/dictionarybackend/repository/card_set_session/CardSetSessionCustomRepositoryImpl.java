package com.luanphm.dictionarybackend.repository.card_set_session;

import com.luanphm.dictionarybackend.constant.ExceptionConstants;
import com.luanphm.dictionarybackend.entity.*;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractSession;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardSetSessionCustomRepositoryImpl extends MyAbstractSession implements CardSetSessionCustomRepository {
    @Override
    @Transactional
    public CardSetSession generateCardSetSession(CardSet cardSet) throws Exception {

        Session session = getSession();


        CardSetSession cardSetSession = CardSetSession
                .builder()
                .id(CommonUtilities.generateUniqueId())
                .cardSet(cardSet)
                .createdDate(CommonUtilities.getCurrentDateTime())
                .build();
        try {
            session.save(cardSetSession);
            List<StudiableCard> studiableCards = generateStudiableCard(cardSetSession, session);
            cardSetSession.setStudiableCards(studiableCards);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(ExceptionConstants.ERROR_WHEN_UPDATE);
        }
        return cardSetSession;
    }
    private List<StudiableCard> generateStudiableCard(CardSetSession cardSetSession, Session session) {
        List<Card> cards = cardSetSession.getCardSet().getCards();
        List<StudiableCard> studiableCards = new ArrayList<>();
        for (Card card : cards) {

            StudiableCard studiableCard = StudiableCard
                    .builder()
                    .id(StudiableCardId.builder().card(card).cardSetSession(cardSetSession).build())
                    .remember(false)
                    .rememberCount(0)
                    .forgetCount(0)
                    .build();

            session.save(studiableCard);
            studiableCards.add(studiableCard);
        }
        return studiableCards;
    }
}
