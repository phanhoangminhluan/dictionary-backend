package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
@EqualsAndHashCode
public class StudiableCardId implements Serializable {

    @OneToOne
    private Card card;

    @ManyToOne
    private CardSetSession cardSetSession;


}
