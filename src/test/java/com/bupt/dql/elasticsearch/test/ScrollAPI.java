package com.bupt.dql.elasticsearch.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForYear;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: mai
 * @date: 2019/9/18
 */
public class ScrollAPI {

    private final static String host = "10.103.250.147";  //10.103.250.143,10.103.246.68,10.103.250.147
    private final static String host1 = "10.103.246.68";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    RestHighLevelClient client = null;
    private String scrollID;
    private int i = 0;
    private int hit_length = 0;   //统计每一次scroll的文档数量，少于size表明已返回所有结果
    private int size = 20000;   //每次scroll返回的结果数量

    @Before
    public void setClient() {
        client = new RestHighLevelClient(
                RestClient.builder(
//                        new HttpHost(host, 9200, "http"),
                        new HttpHost(host1, 9200, "http")
                )
        );
    }


    @After
    public void tearDown() throws IOException {
        //client.close();
        client = null;
    }

    /**
     * 第一次设置scroll
     * @throws IOException
     */
    public void initialScroll() throws IOException {

        SearchRequest searchRequest = new SearchRequest("test3");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("code","es"));
        searchSourceBuilder.size(size);
        searchSourceBuilder.sort("_doc");
//        searchSourceBuilder.fetchSource("user_filename","code");
        searchSourceBuilder.fetchSource(new String[]{"user_filename","filename"},new String[]{"code"});
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        long start_time = System.currentTimeMillis();
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        long end_time = System.currentTimeMillis();
        long time = end_time - start_time;
        scrollID = searchResponse.getScrollId();
        SearchHits searchHits = searchResponse.getHits();
        hit_length = searchHits.getHits().length;   //每一次scroll获得的文档数量

        System.out.println("搜索结果。。。。。。。。。。。。");
        System.out.println("ScrollID: "+scrollID);
        System.out.println("searchHits.getTotalHits().value: "+searchHits.getTotalHits().value);
        System.out.println("searchHits.getHits().length"+searchHits.getHits().length);
//        System.out.println(searchResponse.toString());
//        int i = 0;
        for(SearchHit hit : searchHits.getHits()){
            i++;
            System.out.println(i+": "+hit.getId()+hit.getSourceAsString());

        }
        System.out.println("搜索时间："+time);

    }

    /**
     *
     * @throws IOException
     */
    public void setScroll() throws IOException {

        SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollID);
        searchScrollRequest.scroll(TimeValue.timeValueSeconds(10));
        SearchResponse searchResponse = client.scroll(searchScrollRequest,RequestOptions.DEFAULT);
        scrollID = searchResponse.getScrollId();
        SearchHits searchHits = searchResponse.getHits();
        hit_length = searchHits.getHits().length;   //每一次scroll获得的文档数量

        System.out.println("搜索结果。。。。。。。。。。。。");
        System.out.println("ScrollID: "+scrollID);
        System.out.println("searchHits.getTotalHits().value: "+searchHits.getTotalHits().value);
        System.out.println("searchHits.getHits().length"+searchHits.getHits().length);
//        System.out.println(searchResponse.toString());
//        int i = 0;
        for(SearchHit hit : searchHits.getHits()){
            i++;
            System.out.println(i+": "+hit.getSourceAsString());
        }
        System.out.println();
    }

    @Test
    public void testScroll() throws IOException {
        initialScroll();
        while (hit_length == size){
            setScroll();
        }
//        if(scrollID != null){
//            setScroll();
//        }

    }
}
