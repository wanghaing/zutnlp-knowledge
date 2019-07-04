package edu.zut.cs.zutnlp.knowledge.manager;

import org.elasticsearch.action.search.SearchResponse;

/**
 * Created by lvxin
 */
//参考网站https://blog.csdn.net/SunnyYoona/article/details/52852483
//术语级别查询
public interface TermQueries {

    //查找包含指定字段中指定的确切术语的文档。
    SearchResponse termQuery(String field, Object object);

    //查找包含指定字段中指定的任何确切术语的文档。(也就是说有一个就行)   ，查询中文只能查询一个一个字，
    SearchResponse termsQuery(String field, Object... object);

    //查找指定字段包含指定范围内的值（日期，数字或字符串）的文档。
    SearchResponse rangeQuery(String field, Object ob1, Object ob2);

    //查找指定字段包含任何非空值的文档。存在非null就会返回值
    SearchResponse existsQuery();

    //查找指定字段包含具有指定的确切前缀的术语的文档。
    SearchResponse prefixQuery();

    //查找指定字段包含与指定模式匹配的术语的文档，其中模式支持单字符通配符（?）和多字符通配符（*）
    SearchResponse wildCardQuery();

    //查找指定字段包含与指定的正则表达式匹配的术语的文档。
    SearchResponse regexpQuery();

    //查找指定字段包含与指定术语模糊相似的术语的文档。模糊度是以Levenshtein编辑距离 1或2 来衡量的 。
    SearchResponse fuzzyQuery();

    //查找指定类型的文档
    SearchResponse typeQuery();

    //查找具有指定类型和ID的文档。
    SearchResponse idsQuery();
}
