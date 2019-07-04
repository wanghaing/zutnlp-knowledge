package edu.zut.cs.zutnlp.knowledge.manager.impl;

import edu.zut.cs.zutnlp.knowledge.manager.TermQueries;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lvxin
 */
@Service
public class TermQueriesImpl implements TermQueries {

    @Autowired
    TransportClient client;

    @Override
    public SearchResponse termQuery(String field, Object object) {
        QueryBuilder queryBuilder=QueryBuilders.termQuery(field,object);
        SearchResponse response=client.prepareSearch("zutnlp_index_v1")
                .setQuery(queryBuilder)
                .get();
        return response;
    }

    @Override
    public SearchResponse termsQuery(String field, Object...object) {
        QueryBuilder queryBuilder=QueryBuilders.termsQuery(field,object);
        SearchResponse response=client.prepareSearch()
                .setQuery(queryBuilder)
                .get();
        return response;
    }

    @Override
    public SearchResponse rangeQuery(String field, Object ob1, Object ob2) {
        QueryBuilder queryBuilder=QueryBuilders.rangeQuery(field)
                .from(ob1)
                .to(ob2)
                .includeLower(true)  //包含最低值
                .includeUpper(false); //不包含最大值
        SearchResponse response=client.prepareSearch()
                .setQuery(queryBuilder)
                .get();
        return response;
    }

    @Override
    public SearchResponse existsQuery() {
        return null;
    }

    @Override
    public SearchResponse prefixQuery() {
        return null;
    }

    @Override
    public SearchResponse wildCardQuery() {
        return null;
    }

    @Override
    public SearchResponse regexpQuery() {
        return null;
    }

    @Override
    public SearchResponse fuzzyQuery() {
        return null;
    }

    @Override
    public SearchResponse typeQuery() {
        return null;
    }

    @Override
    public SearchResponse idsQuery() {
        return null;
    }
}
