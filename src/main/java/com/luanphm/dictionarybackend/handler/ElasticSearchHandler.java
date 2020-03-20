package com.luanphm.dictionarybackend.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luanphm.dictionarybackend.constant.CommonConstants;
import com.luanphm.dictionarybackend.constant.ElasticFields;
import com.luanphm.dictionarybackend.entity.BaseEntity;
import com.luanphm.dictionarybackend.utility.ElasticUtilities;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public abstract class ElasticSearchHandler<T extends BaseEntity> {
    @Autowired
    protected ElasticsearchOperations elasticsearchOperations;

    public String save(T document) {
        IndexQuery indexQuery = ElasticUtilities.prepareIndexQuery((String) document.getId(), document);
        return elasticsearchOperations.index(indexQuery);
    }


    public void saveMany(List<T> documents) {
        List<IndexQuery> indexQueries = new ArrayList<>();
        for (T document : documents) {
            IndexQuery indexQuery = ElasticUtilities.prepareIndexQuery((String) document.getId(), document);
        }
        elasticsearchOperations.bulkIndex(indexQueries);
    }

    public T getById(String id) {
        return elasticsearchOperations.queryForObject(GetQuery.getById(id), getIndexClass());
    }

    public List<T> getMany(Map<String, Object> termValuesForMust,
                           Map<String, Object> termValuesForShould,
                           Map<String, Object> termValuesForFilter) {

        BoolQueryBuilder boolQueryBuilder = ElasticUtilities.prepareBoolQueryBuilder(termValuesForMust, termValuesForShould, termValuesForFilter);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();
        return elasticsearchOperations.queryForList(searchQuery, getIndexClass());
    }
    public List<T> getMany(Map<String, Object> termValuesForMust,
                           Map<String, Object> termValuesForShould,
                           Map<String, Object> termValuesForFilter,
                           String fieldSort) {

        BoolQueryBuilder boolQueryBuilder = ElasticUtilities.prepareBoolQueryBuilder(termValuesForMust, termValuesForShould, termValuesForFilter);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withSort(SortBuilders.fieldSort(fieldSort).order(SortOrder.DESC))
                .build();
        return elasticsearchOperations.queryForList(searchQuery, getIndexClass());
    }

    public boolean update(T document) throws JsonProcessingException {
        boolean isExist = getById((String) document.getId()) != null;
        if (isExist) {
            save(document);
            return true;
        }
        return false;
    }
    public void updateMany(List<T> documents) throws JsonProcessingException {
//        List<UpdateQuery> updateQueries = new ArrayList<>();
//        for(T document : documents) {
//            UpdateQuery updateQuery = ElasticUtilities.prepareUpdateQuery(document.getId(), document, getIndexClass());
//            updateQueries.add(updateQuery);
//        }
//        elasticsearchOperations.bulkUpdate(updateQueries);
    }

    public boolean deleteById(String id) {
        boolean isExist = getById(id) != null;
        if (isExist) {
            DeleteQuery deleteQuery = new DeleteQuery();
            deleteQuery.setQuery(QueryBuilders.termQuery(ElasticFields._ID, id));
            deleteQuery.setIndex(getIndexName());
            deleteQuery.setType(CommonConstants._DOC);
            elasticsearchOperations.delete(deleteQuery);
        }
        return isExist;
    }

    public boolean create(T document) {
        try {
            boolean isExist = getById((String) document.getId()) != null;
            if (!isExist) {
                save(document);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public abstract Class<T> getIndexClass();
    public abstract String getIndexName();
}
