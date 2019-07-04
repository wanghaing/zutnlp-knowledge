package edu.zut.cs.zutnlp.knowledge.controller.user.web.spring.contoller;

import edu.zut.cs.zutnlp.knowledge.manager.impl.TermQueriesImpl;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lvxin
 */
@RestController(value = "term")
public class TermQueruesController {

    @Autowired
    TransportClient client;
    @Autowired
    TermQueriesImpl termQueriesImpl;

    @GetMapping("/term")
    public ResponseEntity<?> term(@RequestParam(name = "name")String name,@RequestParam(name="text")Object text){
        if (name==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SearchResponse searchResponse =termQueriesImpl.termQuery(name,text);
        SearchHits hits=searchResponse.getHits();
        String source=null;
        for(SearchHit hit:hits){
            source+=hit.getSourceAsString();
        }

        return  new ResponseEntity<>(source,HttpStatus.OK);
    }

    @GetMapping("/terms")
    public ResponseEntity<?> terms(@RequestParam(name = "name")String name,@RequestParam(name="object")Object...texts){
        if (name==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SearchResponse searchResponse =termQueriesImpl.termsQuery(name,texts);
        SearchHits hits=searchResponse.getHits();
        String source=null;
        for(SearchHit hit:hits){
            source+=hit.getSourceAsString();
        }

        return  new ResponseEntity<>(source,HttpStatus.OK);
    }

    @GetMapping("/range")
    public ResponseEntity<?> range(@RequestParam(name = "name")String name,@RequestParam(name="lower")Object lower,@RequestParam(name="upper")Object upper){
        if (name==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SearchResponse searchResponse =termQueriesImpl.rangeQuery(name,lower,upper);
        SearchHits hits=searchResponse.getHits();
        String source=null;
        for(SearchHit hit:hits){
            source+=hit.getSourceAsString();
        }

        return  new ResponseEntity<>(source,HttpStatus.OK);
    }
}
