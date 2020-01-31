package com.luanphm.dictionarybackend.mapping;

import com.luanphm.dictionarybackend.dto.CardSetDTO;
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
            @Mapping(source = "dto.username", target = "user")
    })
    public abstract CardSet toEntity(CardSetDTO dto);

    @Override
    @Mappings({
            @Mapping(source = "entity.user", target = "username")
    })
    public abstract CardSetDTO toDto(CardSet entity);

    @Override
    public abstract List<CardSet> toEntities(List<CardSetDTO> dtos);

    @Override
    public abstract List<CardSetDTO> toDtos(List<CardSet> entites);
}
