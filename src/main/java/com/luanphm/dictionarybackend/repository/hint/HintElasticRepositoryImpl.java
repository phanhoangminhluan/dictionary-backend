package com.luanphm.dictionarybackend.repository.hint;

import com.google.gson.Gson;
import com.luanphm.dictionarybackend.configuration.elastic.RestClientConfig;
import com.luanphm.dictionarybackend.constant.ElasticIndexes;
import com.luanphm.dictionarybackend.entity.Hint;
import com.luanphm.dictionarybackend.handler.ElasticSearchHandler;
import net.bytebuddy.asm.Advice;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class HintElasticRepositoryImpl extends ElasticSearchHandler<Hint> implements HintRepository {

    @Autowired
    private RestClientConfig restClientConfig;

    @Override
    public List<Hint> getHints(String word) {

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhrasePrefixQuery("word_search", word))
                .withPageable(PageRequest.of(0, 10))
                .withFields("word")
                .build();
        ScrolledPage<Hint> scroll = elasticsearchOperations.startScroll(1000, searchQuery, Hint.class);

        String scrollId = scroll.getScrollId();
        List<Hint> hints = new ArrayList<>();
        if (scroll.hasContent()) {
            hints.addAll(scroll.getContent());
        }
        elasticsearchOperations.clearScroll(scrollId);
        return hints;
    }

    @Override
    public List<Hint> getAll(int page, SortOrder sortOrder) throws IOException {

        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("word", ".*");

        RestHighLevelClient restHighLevelClient = restClientConfig.elasticsearchClient();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(queryBuilder)
                .from(page)
                .size(9500)
                .trackTotalHits(true);
        if (sortOrder != null) {
            searchSourceBuilder.sort("word", sortOrder);
        }

        SearchRequest searchRequest = new SearchRequest("recommendation-words")
                .source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();
        List<Hint> hints = new ArrayList<>();
        for (int i = 0; i < 9500; i++) {
            if (i % 2 != 0) {
                SearchHit searchHit = searchHits.getAt(i);
                String sourceAsString  = searchHit.getSourceAsString();
                if (sourceAsString != null) {
                    Map map = searchHit.getSourceAsMap();
                    String word = map.get("word").toString();
                    Hint hint = Hint.builder().word(word).build();
                    hints.add(hint);
                }
            }
        }

        return hints;
    }

    @Override
    public List<Hint> getAsc(int page) throws IOException {
        return getAll(page, SortOrder.ASC);
    }

    @Override
    public List<Hint> getDesc(int page) throws IOException {
        return getAll(page, SortOrder.DESC);
    }


    @Override
    public void deleteByTerm(String value) {
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setIndex(getIndexName());
        deleteQuery.setType("_doc");
        deleteQuery.setQuery(QueryBuilders.termQuery("word", value));
        elasticsearchOperations.delete(deleteQuery);
    }

    @Override
    public Class<Hint> getIndexClass() {
        return Hint.class;
    }

    @Override
    public String getIndexName() {
        return ElasticIndexes.HINTS;
    }
}
