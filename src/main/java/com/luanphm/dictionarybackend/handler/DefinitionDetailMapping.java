package com.luanphm.dictionarybackend.handler;

import com.luanphm.dictionarybackend.dto.DefinitionDetailDTO;
import com.luanphm.dictionarybackend.entity.word_entity.DefinitionDetail;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class DefinitionDetailMapping extends MappingHelper {

    public abstract DefinitionDetail dtoToDefinitionDetail(DefinitionDetailDTO definitionDetailDTO);
    public abstract DefinitionDetailDTO definitionDetailToDto(DefinitionDetail definitionDetail);

    public abstract List<DefinitionDetail> dtoToEntities(List<DefinitionDetailDTO> definitionDetailDTOS );
    public abstract List<DefinitionDetailDTO> entitiesToDto(List<DefinitionDetail> entities );




}
