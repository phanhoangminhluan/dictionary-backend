package com.luanphm.dictionarybackend.mapping;

import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.entity.word_entity.Pronunciation;
import com.luanphm.dictionarybackend.handler.DefinitionDetailMapping;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class WordDetailMapping extends MappingHelper implements BaseMapping<WordDetail, WordDetailDTO> {


    @Override
    public WordDetail toEntity(WordDetailDTO dto) {
       DefinitionDetailMapping definitionDetailMapping = Mappers.getMapper(DefinitionDetailMapping.class);
        WordDetail wordDetail = WordDetail.builder()
                .word(dto.getWord())
                .pronunciation(Pronunciation.builder().usPhonetic(dto.getUsPhonetic()).ukPhonetic(dto.getUkPhonetic()).build())
                .definitionDetails(definitionDetailMapping.dtoToEntities(dto.getDefinitionDetails()))
                .build();
        return wordDetail;
    }

    @Override
    public  WordDetailDTO toDto(WordDetail entity) {
        DefinitionDetailMapping definitionDetailMapping = Mappers.getMapper(DefinitionDetailMapping.class);
        WordDetailDTO wordDetailDTO = WordDetailDTO.builder()
                .definitionDetails(definitionDetailMapping.entitiesToDto(entity.getDefinitionDetails()))
                .word(entity.getWord())
                .ukPhonetic(entity.getPronunciation().getUkPhonetic())
                .usPhonetic(entity.getPronunciation().getUsPhonetic())
                .build();
        return wordDetailDTO;
    }

    @Override
    public abstract List<WordDetail> toEntities(List<WordDetailDTO> dtos);
    @Override
    public abstract List<WordDetailDTO> toDtos(List<WordDetail> entites);

}
