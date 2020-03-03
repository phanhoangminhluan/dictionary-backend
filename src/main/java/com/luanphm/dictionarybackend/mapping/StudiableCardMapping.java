package com.luanphm.dictionarybackend.mapping;


import com.luanphm.dictionarybackend.dto.StudiableCardDTO;
import com.luanphm.dictionarybackend.dto.StudiableCardIdDTO;
import com.luanphm.dictionarybackend.entity.StudiableCard;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public abstract class StudiableCardMapping extends MappingHelper implements BaseMapping<StudiableCard, StudiableCardDTO> {


    @Override
    public abstract StudiableCard toEntity(StudiableCardDTO dto);

    @Override
    public abstract StudiableCardDTO toDto(StudiableCard entity);

    @Mappings({
            @Mapping(source = "dto.cardId", target = "card"),
            @Mapping(source = "dto.cardSetSessionId", target = "cardSetSession")
    })
    public abstract StudiableCardId toEntity(StudiableCardIdDTO dto);

    @Mappings({
            @Mapping(source = "entity.card", target = "cardId"),
            @Mapping(source = "entity.cardSetSession", target = "cardSetSessionId")
    })
    public abstract  StudiableCardIdDTO toDto(StudiableCardId entity);

    @Override
    public abstract List<StudiableCard> toEntities(List<StudiableCardDTO> dtos);

    @Override
    public abstract List<StudiableCardDTO> toDtos(List<StudiableCard> entites);
}
