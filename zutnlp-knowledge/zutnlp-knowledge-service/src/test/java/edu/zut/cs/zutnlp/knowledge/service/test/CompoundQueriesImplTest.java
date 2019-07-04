package edu.zut.cs.zutnlp.knowledge.service.test;

import edu.zut.cs.zutnlp.knowledge.manager.impl.CompoundQueriesImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CompoundQueriesImplTest{
    @Autowired
    CompoundQueriesImpl compoundQueriesImpl;

    @Test
    public void test(){
//        compoundQueriesImpl.boolQuery("content","社会",
//                "","1547015150000",false);
    }
}
