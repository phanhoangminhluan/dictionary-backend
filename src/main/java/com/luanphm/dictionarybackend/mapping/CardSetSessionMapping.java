package com.luanphm.dictionarybackend.mapping;

import com.luanphm.dictionarybackend.dto.CardSetSessionDTO;
import com.luanphm.dictionarybackend.entity.CardSetSession;
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
            @Mapping(source = "entity.cardSet", target = "cardSetId")
    })
    public abstract CardSetSessionDTO toDto(CardSetSession entity);

    @Override
    public abstract List<CardSetSession> toEntities(List<CardSetSessionDTO> dtos);

    @Override
    public abstract List<CardSetSessionDTO> toDtos(List<CardSetSession> entites);
}
