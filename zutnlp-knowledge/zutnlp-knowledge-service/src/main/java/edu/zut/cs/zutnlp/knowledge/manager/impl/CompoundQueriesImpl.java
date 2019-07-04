package edu.zut.cs.zutnlp.knowledge.manager.impl;

import edu.zut.cs.zutnlp.knowledge.manager.CompoundQueries;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

/**
 * Created by lvxin
 */
@Service
public class CompoundQueriesImpl implements CompoundQueries {

    @Autowired
    TransportClient client;

    @Override
    public SearchResponse boolQuery(String field, String value,String value_not,String time_filter,Boolean number) {
        if (number==false) {
            QueryBuilder qb = QueryBuilders.boolQuery()
                    .must(matchQuery(field, value).operator(Operator.AND))
                    .mustNot(matchQuery(field, value_not))
//                    .filter(rangeQuery("pubTime").format("yyyy-mm-dd").gte(time_filter));
                    .filter(rangeQuery("pubTime").gte(time_filter));
            SearchResponse response=client.prepareSearch("zutnlp_index_v1")
                    .setQuery(qb)
                    .get();
            return response;
        }
        else{
            QueryBuilder qb = QueryBuilders.boolQuery()
                    .must(matchQuery(field, value))
                    .mustNot(matchQuery(field, value_not))
//                    .filter(rangeQuery("pubTime").format("yyyy-mm-dd").gte(time_filter));
                    .filter(rangeQuery("pubTime").gte(time_filter));
            SearchResponse response=client.prepareSearch("zutnlp_index_v1")
                    .setQuery(qb)
                    .get();
            return response;
        }
    }

}
