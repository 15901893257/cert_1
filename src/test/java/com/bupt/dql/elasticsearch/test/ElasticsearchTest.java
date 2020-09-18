package com.bupt.dql.elasticsearch.test;

import com.bupt.dql.pojo.elasticsearch.Coding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class ElasticsearchTest {

    private final static String host = "10.103.250.147";  //10.103.250.143,10.103.246.68,10.103.250.147
    private final static String host1 = "10.103.246.68";
    Logger logger = LoggerFactory.getLogger(this.getClass());
    RestHighLevelClient client = null;
    private Gson gson = new GsonBuilder().create();

    @Before
    public void setClient() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, 9200, "http"),
                        new HttpHost(host1, 9200, "http")
                )
        );
    }


    @After
    public void tearDown() throws IOException {
        //client.close();
        client = null;
    }

    BulkProcessor.Listener listener = new BulkProcessor.Listener() {
        @Override
        public void beforeBulk(long executionId, BulkRequest request) {
            System.out.println("---尝试操作"+request.numberOfActions()+"条数据");

        }
        //执行成功时执行
        @Override
        public void afterBulk(long executionId, BulkRequest request,
                              BulkResponse response) {
            System.out.println("---尝试操作"+request.numberOfActions()+"条数据成功--");

        }
        //执行出错时执行
        @Override
        public void afterBulk(long executionId, BulkRequest request,
                              Throwable failure) {
            System.out.println("---尝试操作"+request.numberOfActions()+"条数据失败--");

        }
    };


    public void bulk(){
        BulkProcessor.Builder builder = BulkProcessor.builder(
                (request, bulkListener) ->
                        client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                listener);
        builder.setBulkActions(100); //设置bulk的执行数，每10000个请求执行批量处理
        builder.setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB));  //设置bulk处理的大小，每5mb冲洗一次
        builder.setFlushInterval(TimeValue.timeValueSeconds(10L));     // 每隔5秒刷新一次
        builder.setConcurrentRequests(1);    // 设置为1，意味着冲洗操作的异步执行
        builder.setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueSeconds(1L), 3)
                );  //设置超时和重调次数


    }


    /**
     * query查询
     */
    @Test
    public void testQuery() throws IOException {
        String param = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
        SearchRequest searchRequest = new SearchRequest("test");
//        searchRequest.routing("Java");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(QueryBuilders.regexpQuery("code",param));
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("code").requireFieldMatch(false);
        highlightBuilder.encoder("html");
//        highlightBuilder.preTags("<span style='color:red'>");
//        highlightBuilder.postTags("</span>");

        searchSourceBuilder.highlighter(highlightBuilder);
        //设置返回文档数量,默认是10
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(200);
        searchSourceBuilder.sort("_doc");
        searchRequest.source(searchSourceBuilder);
        //开始搜索时间
        long startTime = System.currentTimeMillis();
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        System.out.println("搜索结果。。。。。。。。。。。。");
//        System.out.println(searchResponse.toString());
        int i = 0;
        for(SearchHit hit : searchResponse.getHits()){
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
            HighlightField highlightField = highlightFieldMap.get("code");
            Text[] fragments = highlightField.fragments();
            String highlight = "";
            //只取其中一个片段
            highlight  = fragments[0].toString();
//            highlight = highlight.split(">")[1].split("<")[0];
//            Coding coding = gson.fromJson(hit.getSourceAsString(),Coding.class);
//            System.out.println(coding.getFilename());
            System.out.println(highlight);
            i++;
        }
        System.out.println("搜索时间："+time+ "数量："+i);
    }
}

