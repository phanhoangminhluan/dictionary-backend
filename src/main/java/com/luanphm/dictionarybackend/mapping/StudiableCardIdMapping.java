package com.luanphm.dictionarybackend.mapping;

import com.luanphm.dictionarybackend.dto.StudiableCardIdDTO;
import com.luanphm.dictionarybackend.entity.StudiableCardId;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public abstract class StudiableCardIdMapping extends MappingHelper implements BaseMapping<StudiableCardId, StudiableCardIdDTO> {
    @Override
    @Mappings({
            @Mapping(source = "dto.cardId", target = "card"),
            @Mapping(source = "dto.cardSetSessionId", target = "cardSetSession")
    })
    public abstract StudiableCardId toEntity(StudiableCardIdDTO dto);

    @Override
    @Mappings({
            @Mapping(source = "entity.card", target = "cardId"),
            @Mapping(source = "entity.cardSetSession", target = "cardSetSessionId")
    })
    public abstract  StudiableCardIdDTO toDto(StudiableCardId entity);

    @Override
    public abstract  List<StudiableCardId> toEntities(List<StudiableCardIdDTO> dtos);

    @Override
    public abstract  List<StudiableCardIdDTO> toDtos(List<StudiableCardId> entites);}
