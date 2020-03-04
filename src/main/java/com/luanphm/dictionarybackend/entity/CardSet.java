package com.luanphm.dictionarybackend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "card_set")
@Setter
@Getter
@NoArgsConstructor
public class CardSet extends BaseEntity<String> {

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private String createdDate;

    @Builder
    public CardSet(String id, String name, User user, String createdDate, List<Card> cards, List<CardSetSession> cardSetSession) {
        super(id);
        this.name = name;
        this.user = user;
        this.createdDate = createdDate;
        this.cards = cards;
        this.cardSetSession = cardSetSession;
    }

    @OneToMany(mappedBy = "cardSet", cascade = CascadeType.ALL)
    private List<Card> cards;

    @OneToMany(mappedBy = "cardSet", cascade = CascadeType.ALL)
    private List<CardSetSession> cardSetSession;

}
