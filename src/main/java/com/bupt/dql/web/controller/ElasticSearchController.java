package com.bupt.dql.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.dql.service.elasticsearch.ElasticsearchService;
import com.bupt.dql.web.common.BaseController;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.dto.ElasticSearchDTO;
import com.bupt.dql.web.pojo.vo.ElasticCodeVO;

/**
 * @author: mai
 * @date: 2019/7/3
 */
@Controller
@RequestMapping("/es")
public class ElasticSearchController extends BaseController {

    @Resource
    private ElasticsearchService elasticsearchService;

    @RequestMapping("/search")
    public String search() {
        return "es/search";
    }

    @RequestMapping("/index")
    public String index1() {
        return "es/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "es/create";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "es/upload";
    }

    /**
     * 根据索引名称和关键字查询
     * @param param
     * @return
     */
    @RequestMapping("/matchQuery")
    @ResponseBody
    public List<ElasticCodeVO> matchQuery(ElasticSearchDTO param) {
        System.out.println(param.getIndex() + param.getKeyWord());
        List<ElasticCodeVO> response = elasticsearchService.query(param);
        return response;
    }

    /**
     * 根据索引名称和关键字查询
     * @param param
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public JsonResult list(ElasticSearchDTO param) {
        System.out.println(param.getIndex() + param.getKeyWord());
        List<ElasticCodeVO> list = elasticsearchService.query(param);
        return JsonResult.success("操作成功", list, list.size());
    }

}
