package edu.zut.cs.zutnlp.knowledge.dao;


import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lvxin
 */
//Document API
@Repository
public class DocumentDao {

    private final Logger logger=LoggerFactory.getLogger(DocumentDao.class);

    @Autowired
    TransportClient client;

    //创建索引
    public boolean CreateIndex(String index){

        CreateIndexResponse createIndexResponse=client.admin().indices().prepareCreate(index).execute().actionGet();
        return createIndexResponse.isAcknowledged();
    }

    //通过id查询索引信息
    //Map<String,Object> Map<key,value>键值形式，value可以为任何类型，map可以代表json结构
    public Map<String,Object> GetIndexById(String index, String type, String id) {
        if (index==null||type==null||id==null){
            logger.info("查询失败！ ");
        }

        GetResponse getResponse = client.prepareGet(index, type, id).get();
        //返回指定字段- ‘
        System.out.println(getResponse.getSource().get("内容"));
        return getResponse.getSource();
    }

    //更新数据
    public void UpdateIndexById(String data,String index, String type, String id){
        //Json不支持跨域，而Jsonp可以
        UpdateRequest updateRequest=new UpdateRequest().index(index).type(type).id(id).doc(data,XContentType.JSON);
        UpdateResponse updateResponse=client.update(updateRequest).actionGet();
    }

    //通过id删除一条数据
    public void DeleteIndexById(String index,String type,String id){
        DeleteResponse deleteResponse = client.prepareDelete(index, type, id).get();
    }

    //删除整条索引
    public boolean DeleteIndex(String index){
        DeleteIndexResponse deleteIndexResponse=client.admin().indices().prepareDelete(index).execute().actionGet();
        return deleteIndexResponse.isAcknowledged();
    }

    //判断索引是否存在
    public boolean isIndexExit(String index){
        IndicesExistsResponse indicesExistsResponse=client.admin().indices().prepareExists(index).execute().actionGet();
        if (indicesExistsResponse.isExists()){
            logger.info("Index is Existed!",index);
        }
        else{
            logger.info("Index is not Exist!",index);
        }
        return indicesExistsResponse.isExists();
    }

    //添加数据
    public String addDate(String data,String index,String type,String id){
        //判断下一个id是否为空，如果为空，则添加一个id
        if (id==null){
            id=UUID.randomUUID().toString();
        }
        IndexResponse indexResponse=client.prepareIndex(index,type,id).setSource(data,XContentType.JSON)
                .get();
        logger.info("addDate  添加数据的状态： ",indexResponse.status().getStatus());
        //返回一个id
        return indexResponse.getId();
    }

    //通过查询条件删除 Delete by Query API：  异步的方式
    public void DeleteByQuery(String index,String name,String text){
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery(name,text))//查询条件
                .source(index)   //索引名index
                .execute(new ActionListener<BulkByScrollResponse>() {  //监听回调
                    @Override
                    public void onResponse(BulkByScrollResponse bulkByScrollResponse) {
                        Long deleted=bulkByScrollResponse.getDeleted();    //删除文档的数量
                    }

                    @Override
                    public void onFailure(Exception e) {   //异常处理

                    }
                });
    }

    //多文档查询
    public List<String> GetMulti(String index,String type,String...ids){
        List<String> jsonList=new ArrayList<>();
        MultiGetResponse multiGetItemResponses=client.prepareMultiGet().add(index,type,ids).get();//通过多个id的方式
        for (MultiGetItemResponse itemResponse : multiGetItemResponses){ //迭代返回值
            GetResponse response=itemResponse.getResponse();
            if (response.isExists()) {
                jsonList.add(response.getSourceAsString());//_sourse 字段
            }
        }
        return jsonList;
    }
    }
