package com.luanphm.dictionarybackend.mapping;


import com.luanphm.dictionarybackend.dto.*;
import com.luanphm.dictionarybackend.entity.Card;
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
    @Mapping(source = "remember", target = "remember")
    public abstract StudiableCardDTO toDto(StudiableCard entity);

    @Mappings({
            @Mapping(source = "dto.cardId", target = "card"),
            @Mapping(source = "dto.cardSetSessionId", target = "cardSetSession")
    })
    public abstract StudiableCardId toEntity(StudiableCardIdDTO dto);

    @Mapping(source = "remember", target = "remember")
    public abstract StudiableCardLearnDTO toLearnDto(StudiableCard entity);

    @Mappings({
            @Mapping(source = "dto.cardSetSession", target = "cardSetSessionId"),
            @Mapping(source = "card", target = "cardDto")
    })
    public abstract StudiableCardIdLearnDTO toLearnIdDto( StudiableCardId dto);

    @Mappings({
            @Mapping(source = "entity.card", target = "cardId"),
            @Mapping(source = "entity.cardSetSession", target = "cardSetSessionId")
    })
    public abstract  StudiableCardIdDTO toDto(StudiableCardId entity);

    @Override
    public abstract List<StudiableCard> toEntities(List<StudiableCardDTO> dtos);

    @Override
    public abstract List<StudiableCardDTO> toDtos(List<StudiableCard> entites);

    @Mappings({
            @Mapping(target = "cardSet", source = "cardSetId")
    })
    public abstract Card toCardEntity(CardDTO dto);

    @Mappings({
            @Mapping(target = "cardSetId", source = "cardSet")
    })
    public abstract CardDTO toCardDto(Card entity);
}
