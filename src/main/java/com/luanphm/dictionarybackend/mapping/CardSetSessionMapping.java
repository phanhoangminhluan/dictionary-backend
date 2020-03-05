package com.luanphm.dictionarybackend.mapping;

import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSetSession;
import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public abstract class CardSetSessionMapping extends MappingHelper implements BaseMapping<CardSetSession, CardSetSessionDTO> {
    @Override
    @Mappings({
            @Mapping(source = "dto.cardSetId", target = "cardSet")
    })
    public abstract CardSetSession toEntity(CardSetSessionDTO dto);

    @Override
    @Mappings({
            @Mapping(source = "entity.cardSet", target = "cardSetId"),
            @Mapping(source = "studiableCards", target = "studiableCards")
    })
    public abstract CardSetSessionDTO toDto(CardSetSession entity);

    @Override
    public abstract List<CardSetSession> toEntities(List<CardSetSessionDTO> dtos);

    @Override
    public abstract List<CardSetSessionDTO> toDtos(List<CardSetSession> entites);

    public abstract List<StudiableCardDTO> toStudiableCardDtos (List<StudiableCard> studiableCards);


    public StudiableCard studiableCard(StudiableCardDTO studiableCardDto) {
        if (studiableCardDto == null) return null;
        StudiableCard studiableCard = StudiableCard.builder()
                .id(StudiableCardId
                        .builder()
                        .card(Card
                                .builder()
                                .id(studiableCardDto.getCardId())
                                .build())
                        .cardSetSession(CardSetSession
                                .builder()
                                .id(studiableCardDto.getCardSetSessionId())
                                .build())
                        .build())
                .remember(studiableCardDto.isRemember())
                .rememberCount(studiableCardDto.getRememberCount())
                .forgetCount(studiableCardDto.getForgetCount())
                .build();
        return studiableCard;
    }

    public StudiableCardDTO studiableCardDTO (StudiableCard studiableCard) {
        if (studiableCard == null) return null;
        StudiableCardDTO studiableCardDTO = StudiableCardDTO
                .builder()
                .cardId(studiableCard.getId().getCard().getId())
                .cardSetSessionId(studiableCard.getId().getCardSetSession().getId())
                .remember(studiableCard.isRemember())
                .rememberCount(studiableCard.getRememberCount())
                .forgetCount(studiableCard.getForgetCount())
                .build();
        return studiableCardDTO;
    }
}
