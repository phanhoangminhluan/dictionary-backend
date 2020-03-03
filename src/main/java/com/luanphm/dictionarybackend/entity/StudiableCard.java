package com.luanphm.dictionarybackend.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "studiable_card")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class StudiableCard implements Serializable {

    @EmbeddedId
    @JoinColumns({
            @JoinColumn(name = "card_id", referencedColumnName = "id"),
            @JoinColumn(name = "card_set_session_id", referencedColumnName = "id")
    })
    private StudiableCardId id;

    private boolean isRemember;

    @Column(name = "remember_count")
    private int rememberCount;

    @Column(name = "forget_count")
    private int forgetCount;

    public int increaseRememberCount() {
        rememberCount++;
        return rememberCount;
    }

    public int increaseForgetCount() {
        forgetCount++;
        return forgetCount;
    }
}

