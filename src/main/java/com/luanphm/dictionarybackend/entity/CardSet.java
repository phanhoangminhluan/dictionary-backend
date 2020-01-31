package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.*;

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
    public CardSet(String id, String name, User user, String createdDate) {
        super(id);
        this.name = name;
        this.user = user;
        this.createdDate = createdDate;
    }
}
