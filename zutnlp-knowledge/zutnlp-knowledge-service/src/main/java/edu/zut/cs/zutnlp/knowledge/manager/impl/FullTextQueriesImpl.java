package edu.zut.cs.zutnlp.knowledge.manager.impl;

import edu.zut.cs.zutnlp.knowledge.manager.FullTextQueries;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by lvxin
 */
@Service
public class FullTextQueriesImpl implements FullTextQueries {

    private final Logger logger=LoggerFactory.getLogger(FullTextQueriesImpl.class);
    @Autowired
    TransportClient client;

    @Override
    public SearchResponse matchQuery(String index, String type, String field, String value) {
        QueryBuilder queryBuilder=QueryBuilders.matchQuery(field,value);
        SearchResponse response=client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(queryBuilder)
                .get();
        logger.info("matchQuery Response Staus : ",response.status().toString());
        System.out.println(response.toString());
        return response;
    }

    @Override
    public SearchResponse matchPhraseQuery(String index, String type, String field, String value, int n) {
        //slop(n)表示两个词之间可以隔着n个词
        QueryBuilder queryBuilder=QueryBuilders.matchPhraseQuery(field,value).slop(n);
        SearchResponse response=client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(queryBuilder)
                .get();
        System.out.println(response.toString());
        return response;
    }


    @Override
    public SearchResponse multiMatchQuery(String value, String...field) {
        //  .operator
        QueryBuilder queryBuilder=QueryBuilders.multiMatchQuery(value,field);
        SearchResponse response=client.prepareSearch()
                .setQuery(queryBuilder)
                .get();
        return response;
    }

    @Override
    public SearchResponse commonTermsQuery(String field, String value) {
        //  cutoffFrequency设置高频词score因子
        QueryBuilder queryBuilder=QueryBuilders.commonTermsQuery(field,value).cutoffFrequency(0.001f);
        SearchResponse response=client.prepareSearch()
                .setQuery(queryBuilder)
                .get();
        return response;
    }

    @Override
    public SearchResponse queryStringQuery(String field, String value) {
        //field 为检索字段
        QueryBuilder queryBuilder=QueryBuilders.queryStringQuery(value).field(field);
        SearchResponse response=client.prepareSearch("index")
                .setQuery(queryBuilder)
                .get();
        return response;
    }

    @Override
    public SearchResponse simpleQueryStringQuery(String field, String value) {
        QueryBuilder queryBuilder=QueryBuilders.simpleQueryStringQuery(value).field(field);
        SearchResponse response=client.prepareSearch()
                .setQuery(queryBuilder)
                .get();
        return response;
    }

    //查询多个字段，查询不同内容0
    public SearchResponse contentTitleQuery(String field1, String field2, String value1, String value2){
        QueryBuilder qb1=QueryBuilders.matchPhraseQuery(field1,value1);
        QueryBuilder qb2=QueryBuilders.matchPhraseQuery(field2,value2);
        QueryBuilder qb=QueryBuilders.boolQuery().should(qb1).should(qb2);
        SearchResponse response=client.prepareSearch()
                .setQuery(qb).get();
        return response;
    }
}
