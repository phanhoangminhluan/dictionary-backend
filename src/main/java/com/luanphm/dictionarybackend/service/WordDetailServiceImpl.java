package com.luanphm.dictionarybackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.repository.WordDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordDetailServiceImpl implements WordDetailService {

    @Autowired
    private WordDetailRepository wordDetailElasticRepository;

    @Autowired
    private WordDetailRepository wordDetailRapidApiRepository;


    @Override
    public WordDetailDTO getWord(String word) {
        boolean isSuccess = false;
        WordDetail wordDetail = wordDetailElasticRepository.getWord(word);
        if (wordDetail == null) {
            wordDetail = wordDetailRapidApiRepository.getWord(word);
            if (wordDetail != null) {
                wordDetailElasticRepository.saveWord(wordDetail);
                isSuccess = true;
            }
        } else {
            isSuccess = true;
        }
        return isSuccess
                ? WordDetailDTO.builder()
                    .word(wordDetail.getWord())
                    .frequency(wordDetail.getFrequency())
                    .pronunciation(wordDetail.getPronunciation().getAll())
                    .syllableList(wordDetail.getSyllables().getSyllableList())
                    .definitionDetails(wordDetail.getDefinitionDetails())
                .build()
                : null;

    }

    @Override
    public List<WordDetailDTO> getByTerm(String term, String value){
        boolean isSuccess = false;
        List<WordDetail> wordDetails = wordDetailElasticRepository.getByTerm(term, value);
        if (wordDetails == null) {
            wordDetails = wordDetailRapidApiRepository.getByTerm(term, value);
            if (wordDetails != null) {
                isSuccess = true;
            }
        } else {
            isSuccess = true;
        }
        List<WordDetailDTO> dtos = new ArrayList<>();
        if (isSuccess) {
            wordDetails.forEach(detail -> {
                dtos.add(WordDetailDTO.builder()
                            .word(detail.getWord())
                            .frequency(detail.getFrequency())
                            .pronunciation(detail.getPronunciation().getAll())
                            .syllableList(detail.getSyllables().getSyllableList())
                            .definitionDetails(detail.getDefinitionDetails())
                        .build() );
            });
        }
        return isSuccess ? dtos : null;
    }

    @Override
    public boolean update(WordDetailDTO dto) throws JsonProcessingException {
        WordDetail wordDetail = new WordDetail(dto);
        return wordDetailElasticRepository.updateWord(wordDetail);
    }

    @Override
    public boolean delete(String id) {
       return wordDetailElasticRepository.deleteWord(id);
    }
}
