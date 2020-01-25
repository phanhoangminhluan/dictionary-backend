package com.luanphm.dictionarybackend.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanphm.dictionarybackend.entity.Hint;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElasticUtilities {
    public static final List<QueryBuilder> prepareQueryBuilder(Map<String, Object> termValues) {
        List<QueryBuilder> queryBuilders = new ArrayList<>();
        if (termValues == null) {
            return queryBuilders;
        }
        for (String term : termValues.keySet()) {
            Object value = termValues.get(term);
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(term, value);
            queryBuilders.add(termQueryBuilder);
        }
        return queryBuilders;
    }
    public static final BoolQueryBuilder prepareBoolQueryBuilder(Map<String, Object> termValuesForMust,
                                                                 Map<String, Object> termValuesForShould,
                                                                 Map<String, Object> termValuesForFilter) {

        List<QueryBuilder> mustQueryBuilders = prepareQueryBuilder(termValuesForMust);
        List<QueryBuilder> shouldQueryBuilders = prepareQueryBuilder(termValuesForShould);
        List<QueryBuilder> filterQueryBuilders = prepareQueryBuilder(termValuesForFilter);

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        boolQueryBuilder.must().addAll(mustQueryBuilders);
        boolQueryBuilder.should().addAll(shouldQueryBuilders);
        boolQueryBuilder.filter().addAll(filterQueryBuilders);

        return boolQueryBuilder;
    }

//    public static final UpdateQuery prepareUpdateQuery(String id, Object document, Class clazz) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonDoc = objectMapper.writeValueAsString(document);
//
//        UpdateRequest updateRequest = new UpdateRequest()
//                .id(id)
//                .doc(jsonDoc);
//        UpdateQuery updateQuery = new UpdateQueryBuilder()
//                .withUpdateRequest(updateRequest)
//                .withClass(clazz)
//                .build();
//        return updateQuery;
//    }

    public static final IndexQuery prepareIndexQuery(String id, Object document) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(id)
                .withObject(document)
                .build();
        return indexQuery;
    }
}
