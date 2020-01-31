package com.luanphm.dictionarybackend.handler;

import com.luanphm.dictionarybackend.dto.DefinitionDetailDTO;
import com.luanphm.dictionarybackend.entity.word_entity.DefinitionDetail;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;

@Mapper
public abstract class MappingHandler extends MappingHelper {

    public abstract DefinitionDetail dtoToDefinitionDetail(DefinitionDetailDTO definitionDetailDTO);
    public abstract DefinitionDetailDTO definitionDetailToDto(DefinitionDetail definitionDetail);



}
