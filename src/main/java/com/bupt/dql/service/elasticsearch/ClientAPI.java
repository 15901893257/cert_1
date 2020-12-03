package com.bupt.dql.service.elasticsearch;

import static com.bupt.dql.constant.elasticsearch.ElasticsearchConstant.ES_IP_ADDRESS;
import static com.bupt.dql.constant.elasticsearch.ElasticsearchConstant.PORT;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: mai
 * @date: 2019/7/3
 * es client客户端
 */
@Configuration
//@Primary
public class ClientAPI {

    @Bean
    public RestHighLevelClient getClient() {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(ES_IP_ADDRESS, PORT, "http")
                )
        );
        return restHighLevelClient;
    }
}
