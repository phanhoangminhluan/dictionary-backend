package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.entity.Hint;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.repository.hint.HintElasticRepositoryImpl;
import com.luanphm.dictionarybackend.repository.hint.HintRepository;
import com.luanphm.dictionarybackend.service.HintService;
import com.luanphm.dictionarybackend.service.WordDetailService;
import com.luanphm.dictionarybackend.service.WordDetailServiceImpl;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import net.bytebuddy.asm.Advice;
import org.elasticsearch.search.sort.SortOrder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.core.PriorityOrdered;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
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
    private WordDetailService wordDetailService;

    @Autowired
    private HintService hintService;

    @Autowired
    private HintRepository hintRepository;

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

//    @GetMapping("filter-data-asc")
//    public void selectDataAsc() {
//        loopOverHint(2);
//    }
//
//    @GetMapping("filter-data-desc")
//    public void selectDataDesc() {
//        loopOverHint(1);
//    }
//
//    @GetMapping("filter-data-normal")
//    public void selectDataNormal() {
//        loopOverHint(3);
//    }
//
//    @GetMapping("filter-data-reverse")
//    public void selectDataReverse() {
//        loopOverHint(4);
//    }

    @GetMapping("run-crawler")
    public void runCrawler() {
        CrawlerConnector crawlInDesc = new CrawlerConnector(1, wordDetailService, hintRepository);
        CrawlerConnector crawlInAsc = new CrawlerConnector(2, wordDetailService, hintRepository);
        CrawlerConnector crawlerStraight = new CrawlerConnector(3, wordDetailService, hintRepository);
        CrawlerConnector crawlerReverse = new CrawlerConnector(4, wordDetailService, hintRepository);

        crawlInDesc.start();
        crawlInAsc.start();
        crawlerStraight.start();
        crawlerReverse.start();
    }
//
//    public void loopOverHint(int type) {
//        try {
//            System.out.println("START ------------------------------------------: " + CommonUtilities.getCurrentDateTime());
//            for (int i = 1; i <= 30; i++) {
//                List<Hint> hints = null;
//                if (type == 1) {
//                    hints = hintRepository.getDesc(i);
//                } else if (type == 2) {
//                    hints = hintRepository.getAsc(i);
//                } else if (type == 3){
//                    hints = hintRepository.getAll(i, null);
//                } else if (type == 4) {
//                    hints = hintRepository.getAll(20 - i, null);
//                }
//
//                if (hints == null || hints.size() == 0) break;
//                process(hints, type);
//            }
//            System.out.println("END  ------------------------------------------: " + CommonUtilities.getCurrentDateTime());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void process(List<Hint> hints, int type) {
//        int i = 0;
//        int added = 0;
//        String typeStr = "";
//        switch (type) {
//            case 1: typeStr = "DESCENDING";
//                break;
//            case 2: typeStr = "ASCENDING";
//                break;
//            case 3: typeStr = "STRAIGHT";
//                break;
//            case 4: typeStr = "REVERSE";
//                break;
//        }
//        for (Hint hint : hints) {
//            i++;
//            String wordstr = hint.getWord();
//            System.out.print(CommonUtilities.getCurrentDateTime() + " - " + typeStr + " - " + i + " - " + wordstr);
//            WordDetail wordDetail = wordDetailService.getWordNormal(wordstr, type);
//            if (wordDetail != null) {
//                if (wordDetail.getWord().equals("please stop now")) {
//                    System.out.println("[[[[[ - " + wordstr + " - " + added + " - ]]]]]");
//                    return;
//                }
//                added++;
//                System.out.print( " - " + wordDetail.getPronunciation().getUkPhonetic() + " - " + added + " - ADDED");
//            } else {
//                System.out.println( " - DELETED");
//
//                hintRepository.deleteByTerm(wordstr);
//            }
//            System.out.println();
//        }
//    }
}
class CrawlerConnector implements Runnable {

    private Thread thread;
    private int type;

    private WordDetailService wordDetailService;

    private HintRepository hintRepository;
    public CrawlerConnector(int type, WordDetailService wordDetailService, HintRepository hintRepository) {
        this.type = type;
        this.wordDetailService = wordDetailService;
        this.hintRepository = hintRepository;
    }

    @Override
    public void run() {
       loopOverHint(type);
    }

    public void loopOverHint(int type) {
        try {
            System.out.println("START ------------------------------------------: " + CommonUtilities.getCurrentDateTime());
            for (int i = 1; i <= 30; i++) {
                List<Hint> hints = null;
                if (type == 1) {
                    hints = hintRepository.getDesc(i);
                } else if (type == 2) {
                    hints = hintRepository.getAsc(i);
                } else if (type == 3){
                    hints = hintRepository.getAll(i, null);
                } else if (type == 4) {
                    hints = hintRepository.getAll(20 - i, null);
                }

                if (hints == null || hints.size() == 0) break;
                process(hints, type);
            }
            System.out.println("END  ------------------------------------------: " + CommonUtilities.getCurrentDateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void process(List<Hint> hints, int type) {
        int i = 0;
        int added = 0;
        String typeStr = "";
        switch (type) {
            case 1: typeStr = "DESCENDING";
                break;
            case 2: typeStr = "ASCENDING";
                break;
            case 3: typeStr = "STRAIGHT";
                break;
            case 4: typeStr = "REVERSE";
                break;
        }
        for (Hint hint : hints) {
            i++;
            String wordstr = hint.getWord();
            WordDetail wordDetail = wordDetailService.getWordNormal(wordstr, type);
            if (wordDetail != null) {
                if (wordDetail.getWord().equals("please stop now")) {
                    System.out.println("----------------------------------------[[[[[ - " + wordstr + " - " + added + " - ]]]]]----------------------------------------");
                    return;
                }
                added++;
                System.out.println(CommonUtilities.getCurrentDateTime() + " - " + typeStr + " - " + i + " - " + wordstr + " - " + wordDetail.getPronunciation().getUkPhonetic() + " - " + added + " - ADDED");
            } else {
                System.out.println(CommonUtilities.getCurrentDateTime() + " - " + typeStr + " - " + i + " - " + wordstr + " - DELETED");

                hintRepository.deleteByTerm(wordstr);
            }
        }
    }

    public void start() {
        System.out.println("STARTING TYPE " + type);

        thread = new Thread(this, String.valueOf(type));
        thread.start();

    }
}
