package com.luanphm.dictionarybackend.repository.hint;

import com.luanphm.dictionarybackend.constant.ElasticIndexes;
import com.luanphm.dictionarybackend.entity.Hint;
import com.luanphm.dictionarybackend.handler.ElasticSearchHandler;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HintElasticRepositoryImpl extends ElasticSearchHandler<Hint> implements HintRepository {

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
    public List<Hint> getAll(int page, SortOrder sortOrder) {

        SearchQuery searchQuery;
        if (sortOrder == null) {
            searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.regexpQuery("word", ".*"))
                    .withPageable(PageRequest.of(page, 10000))
                    .withFields("word")
                    .build();
        } else {
            searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.regexpQuery("word", ".*"))
                    .withPageable(PageRequest.of(page, 10000))
                    .withSort(SortBuilders.fieldSort("word").order(sortOrder))
                    .withFields("word")
                    .build();
        }
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
    public List<Hint> getAsc(int page) {
        return getAll(page, SortOrder.ASC);
    }

    @Override
    public List<Hint> getDesc(int page) {
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
