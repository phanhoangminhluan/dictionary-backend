package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "card_set_session")
@Getter
@Setter
@NoArgsConstructor
public class CardSetSession extends BaseEntity<String> {

    @Column(name = "created_date")
    private String createdDate;


    @Column(name = "is_open")
    private boolean isOpen;

    @ManyToOne
    @JoinColumn(name = "card_set_id")
    private CardSet cardSet;

    @Builder

    public CardSetSession(String id, String createdDate, boolean isOpen, CardSet cardSet) {
        super(id);
        this.createdDate = createdDate;
        this.isOpen = isOpen;
        this.cardSet = cardSet;
    }
}
