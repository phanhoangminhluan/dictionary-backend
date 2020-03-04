package com.luanphm.dictionarybackend.mapping;

import com.luanphm.dictionarybackend.dto.CardDTO;
import com.luanphm.dictionarybackend.dto.CardInsertDTO;
import com.luanphm.dictionarybackend.entity.Card;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public abstract class CardMapping extends MappingHelper implements BaseMapping<Card, CardDTO> {

    @Override
    @Mappings({
            @Mapping(target = "cardSet", source = "cardSetId")
    })
    public abstract Card toEntity(CardDTO dto);

    @Override
    @Mappings({
        @Mapping(target = "cardSetId", source = "cardSet")
    })
    public abstract CardDTO toDto(Card entity);

    @Override
    public abstract List<Card> toEntities(List<CardDTO> dtos);

    @Override
    public abstract List<CardDTO> toDtos(List<Card> entites);

    public abstract List<Card> toCardsFromCardInsertDto(List<CardInsertDTO> dtos);

}
