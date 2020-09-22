package com.bupt.dql.web.controller;

import com.bupt.dql.pojo.elasticsearch.Coding;
import com.bupt.dql.pojo.param.ElasticParam;
import com.bupt.dql.service.elasticsearch.ElasticsearchService;
import com.bupt.dql.web.enums.ElasticSearchEnum;
import com.bupt.dql.web.pojo.dto.ResultDTO;
import com.bupt.dql.web.pojo.http.request.ElasticSearchParam;
import com.bupt.dql.web.pojo.vo.ElasticCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: mai
 * @date: 2019/7/3
 */
@Controller
@RequestMapping("/es")
public class ElasticSearchController {

    @Resource
    private ElasticsearchService elasticsearchService;

    @RequestMapping("/search")
    public String search(){
        return "elasticsearch/search";
    }

    /**
     * 根据索引名称和关键字查询
     * @param param
     * @return
     */
    @RequestMapping("/matchQuery")
    @ResponseBody
    public List<ElasticCodeVO> matchQuery(ElasticSearchParam param){
        System.out.println(param.getIndex() + param.getKeyWord());
        List<ElasticCodeVO> response = elasticsearchService.query(param);
        return response;
    }


}
