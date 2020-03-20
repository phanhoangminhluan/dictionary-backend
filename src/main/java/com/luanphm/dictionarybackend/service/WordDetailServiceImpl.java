package com.luanphm.dictionarybackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.mapping.WordDetailMapping;
import com.luanphm.dictionarybackend.repository.word_detail.WordDetailRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordDetailServiceImpl implements WordDetailService {

    @Autowired
    private WordDetailRepository wordDetailElasticRepository;

    @Autowired
    private WordDetailRepository wordDetailCambridgeCrawlingRepository;

    private WordDetailMapping wordDetailMapping = Mappers.getMapper(WordDetailMapping.class);


    @Override
    public WordDetailDTO getWord(String word) {
        boolean isSuccess = false;
        WordDetail wordDetail = wordDetailElasticRepository.getWord(word);
        if (wordDetail == null) {
            wordDetail = wordDetailCambridgeCrawlingRepository.getWord(word);
            if (wordDetail != null) {
                wordDetailElasticRepository.saveWord(wordDetail);
                isSuccess = true;
            }
        } else {
            isSuccess = true;
        }
        return isSuccess
                ? wordDetailMapping.toDto(wordDetail)
                : null;
    }

    @Override
    public List<WordDetailDTO> getByTerm(String term, String value){
        boolean isSuccess = false;
        List<WordDetail> wordDetails = wordDetailElasticRepository.getByTerm(term, value);
        if (wordDetails == null) {
            wordDetails = wordDetailCambridgeCrawlingRepository.getByTerm(term, value);
            if (wordDetails != null) {
                isSuccess = true;
            }
        } else {
            isSuccess = true;
        }
        List<WordDetailDTO> dtos = wordDetailMapping.toDtos(wordDetails);
        return isSuccess ? dtos : null;
    }

    @Override
    public boolean update(WordDetailDTO dto) throws JsonProcessingException {
        if (dto == null) return false;
        WordDetail wordDetail = wordDetailMapping.toEntity(dto);
        return wordDetailElasticRepository.updateWord(wordDetail);
    }

    @Override
    public boolean delete(String id) {
       return wordDetailElasticRepository.deleteWord(id);
    }

    @Override
    public boolean create(WordDetailDTO wordDetailDTO) {
        if (wordDetailDTO == null) return false;
        WordDetail wordDetail = wordDetailMapping.toEntity(wordDetailDTO);
        wordDetail.setId(wordDetail.getWord());
        return wordDetailElasticRepository.createWord(wordDetail);
    }
}
