package com.luanphm.dictionarybackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "card_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardStatus extends BaseEntity<Integer> {

    private String name;

    public static final int REMAINING_ID = 1;
    public static final int FAMILIAR_ID = 2;
    public static final int MASTERED_ID = 3;
}
