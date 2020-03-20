package com.luanphm.dictionarybackend.utility;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

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

    public static final IndexQuery prepareIndexQuery(String id, Object document) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(id)
                .withObject(document)
                .build();
        return indexQuery;
    }
}
