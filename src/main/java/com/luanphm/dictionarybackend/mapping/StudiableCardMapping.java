package com.luanphm.dictionarybackend.mapping;


import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.*;
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
public abstract class StudiableCardMapping extends MappingHelper implements BaseMapping<StudiableCard, StudiableCardDTO> {


    public StudiableCardDTO toDto (StudiableCard studiableCard) {
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

    public StudiableCard toEntity(StudiableCardDTO studiableCardDto) {
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

    @Mappings({
            @Mapping(source = "dto.cardId", target = "card"),
            @Mapping(source = "dto.cardSetSessionId", target = "cardSetSession")
    })
    public abstract StudiableCardId toEntity(StudiableCardIdDTO dto);

    public StudiableCardLearnDTO toLearnDto(StudiableCard entity) throws Exception {
        if (entity == null) return null;
        StudiableCardLearnDTO studiableCardDTO = StudiableCardLearnDTO
                .builder()
                .cardId(entity.getId().getCard().getId())
                .cardSetId(entity.getId().getCard().getCardSet().getId())
                .cardSetSessionId(entity.getId().getCardSetSession().getId())
                .term(entity.getId().getCard().getTerm())
                .definition(entity.getId().getCard().getDefinition())
                .remember(entity.isRemember())
                .rememberCount(entity.getRememberCount())
                .forgetCount(entity.getForgetCount())
                .username(SecurityUtils.getCurrentUser())
                .build();
        return studiableCardDTO;
    }

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
