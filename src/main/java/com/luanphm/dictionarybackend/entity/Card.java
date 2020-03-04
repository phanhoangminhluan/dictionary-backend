package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity<String> {

    private String term;

    private String definition;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "card_set_id")
    private CardSet cardSet;

    @Builder
    public Card(String id, String term, String definition, CardSet cardSet) {
        super(id);
        this.term = term;
        this.definition = definition;
        this.cardSet = cardSet;
    }
}
