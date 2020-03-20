package com.luanphm.dictionarybackend.mapping;

import com.luanphm.dictionarybackend.dto.FavoriteWordDTO;
import com.luanphm.dictionarybackend.entity.FavoriteWord;
import com.luanphm.dictionarybackend.mapping.common.BaseMapping;
import com.luanphm.dictionarybackend.mapping.common.MappingHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public abstract class FavoriteWordMapping extends MappingHelper implements BaseMapping<FavoriteWord, FavoriteWordDTO> {

    @Override
    @Mapping(source = "word", target = "word")
    public abstract FavoriteWord toEntity(FavoriteWordDTO dto);

    @Override
    public abstract FavoriteWordDTO toDto(FavoriteWord entity);

    @Override
    public abstract List<FavoriteWord> toEntities(List<FavoriteWordDTO> dtos);

    @Override
    public abstract List<FavoriteWordDTO> toDtos(List<FavoriteWord> entites);
}
