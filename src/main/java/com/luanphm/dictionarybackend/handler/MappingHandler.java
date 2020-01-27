package com.luanphm.dictionarybackend.handler;

import com.luanphm.dictionarybackend.dto.DefinitionDetailDTO;
import com.luanphm.dictionarybackend.dto.SyllablesDTO;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.entity.word_entity.DefinitionDetail;
import com.luanphm.dictionarybackend.entity.word_entity.Syllables;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface MappingHandler {

    DefinitionDetail dtoToDefinitionDetail(DefinitionDetailDTO definitionDetailDTO);
    DefinitionDetailDTO definitionDetailToDto(DefinitionDetail definitionDetail);
}
