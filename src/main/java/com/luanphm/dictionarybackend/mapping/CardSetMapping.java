package com.luanphm.dictionarybackend.mapping;

import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.dto.CardInsertDTO;
import com.luanphm.dictionarybackend.dto.CardSetDTO;
import com.luanphm.dictionarybackend.dto.CardSetInsertDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.entity.CardSet;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public abstract class CardSetMapping extends MappingHelper implements BaseMapping<CardSet, CardSetDTO> {

    @Override
    @Mappings({
            @Mapping(source = "dto.username", target = "user"),
            @Mapping(source = "cards", target = "cards"),
            @Mapping(source = "cardSetSessionId", target = "cardSetSession")
    })
    public abstract CardSet toEntity(CardSetDTO dto);

    @Override
    @Mappings({
            @Mapping(source = "entity.user", target = "username"),
            @Mapping(source = "cardSetSession", target = "cardSetSessionId")
    })
    public abstract CardSetDTO toDto(CardSet entity);

    @Override
    public abstract List<CardSet> toEntities(List<CardSetDTO> dtos);

    @Override
    public abstract List<CardSetDTO> toDtos(List<CardSet> entites);

    @Mapping(source = "cardSetId", target = "cardSet")
    public abstract Card toCard(CardDTO dto);

    public abstract List<Card> toCards(List<CardDTO> dtos);

    public abstract List<CardDTO> toCardDtos(List<Card> entities);

    @Mappings({
            @Mapping(target = "cardSetId", source = "cardSet")
    })
    public abstract CardDTO toCardDto(Card entity);

    public abstract Card toCard(CardInsertDTO dto);

    public abstract List<Card> toCardsFromCardInsertDto(List<CardInsertDTO> dtos);

    public abstract CardSet toCardSet(CardSetInsertDTO cardSetInsertDTO);
}
