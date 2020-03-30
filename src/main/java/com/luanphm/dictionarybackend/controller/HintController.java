package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.entity.Hint;
import com.luanphm.dictionarybackend.service.HintService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "hint")
public class HintController {

    private final File JSON_FILE = new File("src/main/resources/static/words_dictionary.json").getAbsoluteFile();

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private HintService hintService;

    @GetMapping("{word}")
    public ResponseEntity getHints(@PathVariable String word) {
        List<String> hints = hintService.getHints(word);
        return hints.size() != 0
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, hints, HttpStatus.OK)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.WORD_NOT_FOUND, ResponseDTO.EMPTY_BODY, HttpStatus.NOT_FOUND);
    }

    @GetMapping("migrate-data-to-elastic")
    public void saveRecomWordToElastic() {
        JSONParser jsonParser = new JSONParser();
        List<Hint> hints = new ArrayList<>();
        List<IndexQuery> indexQueries = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(JSON_FILE);
            Object obj = jsonParser.parse(fileReader);
            JSONObject wordList = (JSONObject) obj;
            System.out.println(wordList.keySet().size());
            for (Object word : wordList.keySet()) {
                Hint hint = Hint
                        .builder()
                        .word(word.toString())
                        .wordText(word.toString())
                        .wordSearch(word.toString())
                        .build();
                hint.setId(word.toString());
                IndexQuery indexQuery = new IndexQueryBuilder()
                        .withId(UUID.randomUUID().toString())
                        .withObject(hint)
                        .build();
                indexQueries.add(indexQuery);
            }
            elasticsearchOperations.bulkIndex(indexQueries);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *         <dependency>
     *             <groupId>com.googlecode.json-simple</groupId>
     *             <artifactId>json-simple</artifactId>
     *             <version>1.1.1</version>
     *         </dependency>
     */

}
