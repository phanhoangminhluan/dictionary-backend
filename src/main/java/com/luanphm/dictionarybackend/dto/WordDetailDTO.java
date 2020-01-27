package com.luanphm.dictionarybackend.dto;

import com.luanphm.dictionarybackend.entity.word_entity.DefinitionDetail;
import com.luanphm.dictionarybackend.handler.MappingHandler;
import lombok.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class WordDetailDTO {

    private String word;
    private List<DefinitionDetailDTO> definitionDetails;
    private List<String> syllableList;
    private String pronunciation;
    private int frequency;

    public static class WordDetailDTOBuilder {

        private List<DefinitionDetailDTO> definitionDetails = new ArrayList<>();
        private MappingHandler mappingHandler = Mappers.getMapper(MappingHandler.class);
        public WordDetailDTOBuilder definitionDetails(List<DefinitionDetail> definitionDetail) {
            definitionDetail.forEach( detail -> {
                this.definitionDetails.add(mappingHandler.definitionDetailToDto(detail));
            });
            return this;
        }
    }
}




