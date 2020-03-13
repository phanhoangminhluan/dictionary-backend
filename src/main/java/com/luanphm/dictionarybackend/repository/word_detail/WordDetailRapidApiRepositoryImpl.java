package com.luanphm.dictionarybackend.repository.word_detail;

import com.luanphm.dictionarybackend.constant.CommonConstants;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.entity.word_entity.DefinitionDetail;
import com.luanphm.dictionarybackend.entity.word_entity.Pronunciation;
import com.luanphm.dictionarybackend.handler.RapidApiRequestHandler;
import com.luanphm.dictionarybackend.utility.Navigator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "wordDetailRapidApiRepository")
public class WordDetailRapidApiRepositoryImpl implements WordDetailRepository {

    @Autowired
    private RapidApiRequestHandler<WordDetail> rapidApiRequestHandler;
    private Navigator navigator;

    public WordDetailRapidApiRepositoryImpl() {
         navigator = new Navigator("drivers/chromedriver");
    }


    @Override
    public WordDetail getWord(String word) {

        WebDriver driver = navigator.getDriver();
        navigator.navigateTo(CommonConstants.BASE_URL + word);

        if (driver.getCurrentUrl().equals("https://dictionary.cambridge.org/vi/dictionary/english/")) return null;

        String ukPhonetic = navigator.getText(CommonConstants.UK_PHONETIC);
        String usPhonetic = navigator.getText(CommonConstants.US_PHONETIC);

        List<WebElement> definitionBlocks = driver.findElements(By.cssSelector(CommonConstants.DEFINITION_BLOCKS));
        List<DefinitionDetail> definitionDetails = new ArrayList<>();
        for (WebElement definitionBlock : definitionBlocks) {

            String partOfSpeech = navigator.getText(CommonConstants.PART_OF_SPEECH);
            String level = navigator.getText(CommonConstants.LEVEL, definitionBlock);
            String definition =  navigator.getText(CommonConstants.DEFINITION, definitionBlock);
            List<String> examples = navigator.getTexts(CommonConstants.EXAMPLES, definitionBlock);
            List<String> synonyms = navigator.getAttributes(CommonConstants.SYNONYMS, "title", definitionBlock);

            DefinitionDetail definitionDetail = DefinitionDetail.builder()
                    .definition(definition)
                    .partOfSpeech(partOfSpeech)
                    .level(level)
                    .synonyms(synonyms)
                    .examples(examples)
                    .build();

            definitionDetails.add(definitionDetail);
        }

        WordDetail wordDetail = WordDetail.builder()
                .word(word)
                .definitionDetails(definitionDetails)
                .pronunciation(Pronunciation.builder().usPhonetic(usPhonetic).ukPhonetic(ukPhonetic).build())
                .build();
        return wordDetail;
    }

    @Override
    public List<WordDetail> getByTerm(String term, String value) {
        return null;
    }

    @Override
    public void saveWord(WordDetail wordDetail) {

    }
    @Override
    public boolean updateWord(WordDetail wordDetail) {

        return false;
    }
    @Override
    public boolean deleteWord(String id) {

        return false;
    }

    @Override
    public boolean createWord(WordDetail wordDetail) {
        return false;
    }

}
