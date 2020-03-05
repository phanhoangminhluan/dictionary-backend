package com.luanphm.dictionarybackend.mapping.common;

import com.luanphm.dictionarybackend.entity.*;

import java.util.ArrayList;
import java.util.List;

public class MappingHelper {

    protected String cardSetId(CardSet cardSet) {
        if (cardSet == null) {
            return null;
        }
        return cardSet.getId();
    }

    protected CardSet cardSet (String cardSetId) {
        if (cardSetId == null) {
            return null;
        }
        CardSet cardSet = new CardSet();
        cardSet.setId(cardSetId);
        return cardSet;
    }

    public String username(User user) {
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public User user(String username) {
        if (username == null) {
            return null;
        }
        User user = new User();
        user.setId(username);
        return user;
    }

    public CardStatus cardStatus(int cardStatusId) {
        CardStatus cardStatus = new CardStatus();
        cardStatus.setId(cardStatusId);
        return cardStatus;
    }

    public Integer cardStatusName(CardStatus cardStatus) {
        if (cardStatus == null) {
            return null;
        }
        return cardStatus.getId();
    }

    public CardSetSession cardSetSession(String cardSetSessionId) {
        if (cardSetSessionId == null) {
            return null;
        }
        CardSetSession cardSetSession = new CardSetSession();
        cardSetSession.setId(cardSetSessionId);
        return cardSetSession;
    }

    public String cardSetSessionId(CardSetSession cardSetSession) {
        if (cardSetSession == null) {
            return null;
        }
        return cardSetSession.getId();
    }

    public List<CardSetSession> cardSetSessions(String cardSetSessionId) {
        if (cardSetSessionId == null) {
            return null;
        }
        List<CardSetSession> cardSetSessions = new ArrayList<>();
        CardSetSession cardSetSession = new CardSetSession();
        cardSetSession.setId(cardSetSessionId);
        cardSetSessions.add(cardSetSession);
        return cardSetSessions;
    }

    public String cardSetSessionIds(List<CardSetSession> cardSetSessions) {
        if (cardSetSessions == null || cardSetSessions.size() == 0) {
            return null;
        }
        return cardSetSessions.get(0).getId();
    }


    public Card card(String cardId) {
        if (cardId == null) {
            return null;
        }
        Card card = new Card();
        card.setId(cardId);
        return card;
    }

    public String cardId(Card card) {
        if (card == null) {
            return null;
        }
        return card.getId();
    }

}
