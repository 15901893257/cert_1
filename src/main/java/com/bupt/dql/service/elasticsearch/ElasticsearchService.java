package com.bupt.dql.service.elasticsearch;

import com.bupt.dql.constant.GlobalConstant;
import com.bupt.dql.constant.elasticsearch.ElasticsearchConstant;
import com.bupt.dql.pojo.elasticsearch.Coding;
import com.bupt.dql.pojo.elasticsearch.IndexNameChange;
import com.bupt.dql.pojo.param.ElasticParam;
import com.bupt.dql.web.enums.ElasticIndexEnum;
import com.bupt.dql.web.enums.ElasticSearchEnum;
import com.bupt.dql.web.enums.IndexEnum;
import com.bupt.dql.web.exception.ParamException;
import com.bupt.dql.web.pojo.http.request.ElasticSearchParam;
import com.bupt.dql.web.pojo.vo.ElasticCodeVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: mai
 * @date: 2019/7/3
 */
@Service
@Slf4j
public class ElasticsearchService {

    private Gson gson = new Gson();

    @Resource
    private RestHighLevelClient restHighLevelClient;

    public List<ElasticCodeVO> query(ElasticSearchParam param){
        check(param);

        SearchRequest searchRequest = new SearchRequest(ElasticIndexEnum.getName(param.getIndex()));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        setSearchSourceBuilder(searchSourceBuilder, param);

        //显示高亮部分
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field(ElasticsearchConstant.CODE_FILED).requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        searchSourceBuilder.highlighter(highlightBuilder);
        //设置返回文档数量,默认是10
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(ElasticsearchConstant.ES_RESULT_SIZE);
        searchSourceBuilder.sort(ElasticsearchConstant.ORDER_FIELD);
        //设置返回的字段，过滤code
        searchSourceBuilder.fetchSource(ElasticsearchConstant.USER_FILENAME_FILED, ElasticsearchConstant.CODE_FILED);
        searchRequest.source(searchSourceBuilder);
        //开始搜索时间
        long startTime = System.currentTimeMillis();
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        List<ElasticCodeVO> elasticCodeVOList = new ArrayList<>();
        if (searchResponse == null) {
            return elasticCodeVOList;
        }
        for(SearchHit hit : searchResponse.getHits()){
//                System.out.println(hit.getSourceAsString());
            ElasticCodeVO elasticCodeVO = gson.fromJson(hit.getSourceAsString(),ElasticCodeVO.class);

            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
            HighlightField highlightField = highlightFieldMap.get("code");
            Text[] fragments = highlightField.fragments();
            String highlight = "";
            //取两个片段
            for (int i = 0; i < 2 && i < fragments.length; i++) {
                highlight += fragments[i].toString();
            }
            //highlight = fragments[0].toString();

            elasticCodeVO.setHighlight(highlight);
            elasticCodeVO.setLanguage(IndexEnum.getName(param.getIndex()));

            elasticCodeVOList.add(elasticCodeVO);
        }
        log.info("搜索时间："+time);
        return elasticCodeVOList;
    }

    private void setSearchSourceBuilder(SearchSourceBuilder searchSourceBuilder, ElasticSearchParam param){
        //模糊查询
        if (param.getType() == 0) {
            searchSourceBuilder.query(QueryBuilders.matchQuery(ElasticsearchConstant.CODE_FILED, param.getKeyWord()));
        } else if (param.getType() == 1) {
            //正则匹配
            searchSourceBuilder.query(QueryBuilders.regexpQuery(ElasticsearchConstant.CODE_FILED, param.getKeyWord()));
        }
    }

    private void check(ElasticSearchParam param){
        if (param == null) {
            throw new ParamException(GlobalConstant.PARAM_ERROR);
        }
        if (param.getType() == null || param.getIndex() == null) {
            throw new ParamException(GlobalConstant.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(param.getKeyWord())) {
            throw new ParamException("请输入关键字！");
        }
    }

}
