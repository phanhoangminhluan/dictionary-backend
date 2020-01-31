package com.luanphm.dictionarybackend.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studiable_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StudiableCard extends BaseEntity<StudiableCardId> {


    @ManyToOne
    @JoinColumn(name = "card_status_id")
    private CardStatus cardStatus;
}

