package com.bupt.dql.elasticsearch.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: mai
 * @date: 2019/7/10
 * 批处理
 */
public class BulkProcessorAPI {
    private final static String host = "10.103.246.68";  //10.103.250.143,10.103.246.68
    private final static String host1 = "10.103.245.148";
    private final static String host2 = "10.103.245.149";
    private final static String host_1 = "10.103.250.146";
    Logger logger = LoggerFactory.getLogger(this.getClass());
//    RestHighLevelClient client = null;


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

    /**
     * client连接
     * @return
     */
    public RestHighLevelClient getClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, 9200, "http"),
                        new HttpHost(host1, 9200, "http"),
                        new HttpHost(host2, 9200, "http")
                )
        );
        return client;
    }

    /**
     * 批处理操作
     * 10000条时发生了oom
     * @return
     */
    public BulkProcessor getBulkProcessor() {
        BulkProcessor bulkProcessor = BulkProcessor.builder(
                (request, bulkListener) ->
                        getClient().bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                listener)
                .setBulkActions(100)   //设置bulk的执行数，每10000个请求执行批量处理
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))  //设置bulk处理的大小，每5mb冲洗一次
                .setFlushInterval(TimeValue.timeValueSeconds(10L))     // 每隔5秒刷新一次
                .setConcurrentRequests(1)    // 设置为1，意味着冲洗操作的异步执行
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueSeconds(1L), 3)
                )    //设置超时和重调次数
                .build();
        return bulkProcessor;
    }
}
