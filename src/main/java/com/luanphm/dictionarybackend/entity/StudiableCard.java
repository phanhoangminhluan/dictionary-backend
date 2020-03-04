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

    @Column(name = "is_remember")
    private boolean remember;

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Column(name = "remember_count")
    private int rememberCount;

    @Column(name = "forget_count")
    private int forgetCount;

    public int increaseRememberCount() {
        rememberCount++;
        remember = true;
        return rememberCount;
    }

    public int increaseForgetCount() {
        forgetCount++;
        remember = false;
        return forgetCount;
    }
}

