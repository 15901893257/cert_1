package com.bupt.dql.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.dql.service.ISensitiveService;
import com.bupt.dql.web.common.BaseController;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.enums.StatusEnum;
import com.bupt.dql.web.pojo.entity.SensitiveWordsDO;
import com.bupt.dql.web.pojo.vo.SensitiveWordsVO;
import com.bupt.dql.web.query.SensitiveWordsQuery;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
@Slf4j
@Controller
@RequestMapping("/words")
public class SensitiveWordsController extends BaseController {

    @Resource
    private ISensitiveService sensitiveService;

    @PostMapping("/list")
    @ResponseBody
    public JsonResult list(SensitiveWordsQuery query) {
        return sensitiveService.getList(query);
    }

    @PostMapping("/add")
    @ResponseBody
    public JsonResult add(@RequestBody SensitiveWordsVO sensitiveWordsVO, HttpServletRequest request) {
        log.info("新增敏感词，sensitiveWordsVO：{}", sensitiveWordsVO);
        //存入会话session
        HttpSession session = request.getSession();
        SensitiveWordsDO sensitiveWordsDO = new SensitiveWordsDO();
        BeanUtils.copyProperties(sensitiveWordsVO, sensitiveWordsDO);

        long cur = System.currentTimeMillis();
        sensitiveWordsDO.setCtime(cur);
        sensitiveWordsDO.setUtime(cur);
        sensitiveWordsDO.setOperator((String) session.getAttribute("username"));
        sensitiveWordsDO.setStatus(StatusEnum.VALID.getCode());

        sensitiveService.insertOrUpdate(sensitiveWordsDO);
        return JsonResult.success("新增敏感词成功");
    }
}
