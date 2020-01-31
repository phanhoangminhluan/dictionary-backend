package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity<String> {

    private String term;

    private String definition;

    @Column(name = "remember_count")
    private int rememberCount;

    @Column(name = "forget_count")
    private int forgetCount;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "card_set_id")
    private CardSet cardSet;

    @Builder
    public Card(String id, String term, String definition, int rememberCount, int forgetCount, CardSet cardSet) {
        super(id);
        this.term = term;
        this.definition = definition;
        this.rememberCount = rememberCount;
        this.forgetCount = forgetCount;
        this.cardSet = cardSet;
    }
}
