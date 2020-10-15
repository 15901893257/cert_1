package com.bupt.dql.service.elasticsearch;

import com.bupt.dql.constant.elasticsearch.ElasticsearchConstant;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
                        new HttpHost(ElasticsearchConstant.ES_IP_ADDRESS, 9200, "http")
                )
        );
        return restHighLevelClient;
    }
}
