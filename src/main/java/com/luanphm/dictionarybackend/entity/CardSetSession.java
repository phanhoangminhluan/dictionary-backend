package com.luanphm.dictionarybackend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "card_set_id")
    private CardSet cardSet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "card_set_session_id")
    })
    private List<StudiableCard> studiableCards;

    @Builder
    public CardSetSession(String id, String createdDate, boolean isOpen, CardSet cardSet, List<StudiableCard> studiableCards) {
        super(id);
        this.createdDate = createdDate;
        this.isOpen = isOpen;
        this.cardSet = cardSet;
        this.studiableCards = studiableCards;
    }
}
