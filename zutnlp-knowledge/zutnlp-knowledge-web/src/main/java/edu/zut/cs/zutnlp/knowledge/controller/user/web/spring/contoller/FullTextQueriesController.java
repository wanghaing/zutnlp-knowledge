package edu.zut.cs.zutnlp.knowledge.controller.user.web.spring.contoller;

import edu.zut.cs.zutnlp.knowledge.manager.impl.FullTextQueriesImpl;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author lvxin
 * @date 18-11-8 下午1:45
 */
@RestController
public class FullTextQueriesController {

    @Autowired
    TransportClient client;
    @Autowired
    FullTextQueriesImpl ftqi;

    @GetMapping("/str")
    public ResponseEntity<?> queryString(@RequestParam(name = "field")String field, @RequestParam(name="value")String  value){
        if (field==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SearchResponse searchResponse =ftqi.queryStringQuery(field,value);
        SearchHits hits=searchResponse.getHits();
        String source=null;
        for(SearchHit hit:hits){
            source+=hit.getSourceAsString();
        }

        return  new ResponseEntity<>(source, HttpStatus.OK);
    }
}
