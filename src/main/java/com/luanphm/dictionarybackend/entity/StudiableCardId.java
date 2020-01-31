package com.luanphm.dictionarybackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public
class StudiableCardId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "card_set_id")
    private CardSet cardSet;

    @ManyToOne
    @JoinColumn(name = "card_set_session_id")
    private CardSetSession cardSetSession;


}
