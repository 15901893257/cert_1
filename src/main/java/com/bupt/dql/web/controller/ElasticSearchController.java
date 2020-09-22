package com.bupt.dql.web.controller;

import com.bupt.dql.service.elasticsearch.ElasticsearchService;
import com.bupt.dql.web.pojo.dto.ElasticSearchDTO;
import com.bupt.dql.web.pojo.vo.ElasticCodeVO;
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
    public List<ElasticCodeVO> matchQuery(ElasticSearchDTO param){
        System.out.println(param.getIndex() + param.getKeyWord());
        List<ElasticCodeVO> response = elasticsearchService.query(param);
        return response;
    }


}
