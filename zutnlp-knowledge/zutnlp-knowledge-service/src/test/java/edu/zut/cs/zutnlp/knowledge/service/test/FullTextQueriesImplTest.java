package edu.zut.cs.zutnlp.knowledge.service.test;

import edu.zut.cs.zutnlp.knowledge.manager.impl.FullTextQueriesImpl;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FullTextQueriesImplTest {
    @Autowired
    FullTextQueriesImpl fullTextQueriesImpl;

    @Test
    public void test(){
        fullTextQueriesImpl.
                simpleQueryStringQuery("content","轻度污染");
    }
}
