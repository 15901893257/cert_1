package com.bupt.dql.web.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.dql.common.util.DateUtil;
import com.bupt.dql.common.util.MD5Util;
import com.bupt.dql.service.ISensitiveService;
import com.bupt.dql.web.common.BaseController;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.enums.StatusEnum;
import com.bupt.dql.web.pojo.entity.SensitiveWordsDO;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.vo.SensitiveWordsVO;
import com.bupt.dql.web.pojo.vo.SysUserVO;
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
        String operator = (String) session.getAttribute("username");
        SensitiveWordsDO sensitiveWordsDO = getSensitiveWordsDO(sensitiveWordsVO, operator);

        sensitiveService.insertOrUpdate(sensitiveWordsDO);
        return JsonResult.success("新增敏感词成功");
    }

    private SensitiveWordsDO getSensitiveWordsDO(@RequestBody SensitiveWordsVO sensitiveWordsVO, String operator) {
        SensitiveWordsDO sensitiveWordsDO = new SensitiveWordsDO();
        BeanUtils.copyProperties(sensitiveWordsVO, sensitiveWordsDO);

        long cur = System.currentTimeMillis();
        sensitiveWordsDO.setCtime(cur);
        sensitiveWordsDO.setUtime(cur);
        sensitiveWordsDO.setOperator(operator);
        sensitiveWordsDO.setStatus(StatusEnum.VALID.getCode());
        return sensitiveWordsDO;
    }

    @Override
    public String edit(Long id, Model model) {
        log.info("根据id查询敏感词,id: {}", id);
        SensitiveWordsVO sensitiveWordsVO = new SensitiveWordsVO();
        if (id != null && id > 0) {
            SensitiveWordsDO sensitiveWordsDO = sensitiveService.queryById(id);
            BeanUtils.copyProperties(sensitiveWordsDO, sensitiveWordsVO);
            model.addAttribute("info", sensitiveWordsVO);
        }
        return super.edit(id, model);
    }

    /**
     * 更新敏感词
     * @param sensitiveWordsVO
     * @param request
     * @return
     * @throws ParseException
     */
    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestBody SensitiveWordsVO sensitiveWordsVO, HttpServletRequest request) throws ParseException {
        log.info("更新敏感词，sensitiveWordsVO: {}", sensitiveWordsVO);
        HttpSession session = request.getSession();
        String operator = (String) session.getAttribute("username");
        SensitiveWordsDO sensitiveWordsDO = getSensitiveWordsDO(sensitiveWordsVO, operator);

        sensitiveService.insertOrUpdate(sensitiveWordsDO);
        return JsonResult.success("更新敏感词成功");
    }

    /**
     * 删除记录
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public JsonResult delete(@PathVariable("id") Long id) {
        log.info("删除敏感词，id:{}", id);
        int result = sensitiveService.deleteById(id);
        String msg = "";
        if (result == 0) {
            msg = "删除失败";
        } else {
            msg = "删除成功";
        }
        return JsonResult.success(msg);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @GetMapping("/batchDelete/{ids}")
    @ResponseBody
    public JsonResult batchDelete(@PathVariable("ids") List<Long> ids) {
        log.info("批量删除敏感词，ids:{}", ids);
        int result = sensitiveService.batchDelete(ids);
        String msg = "";
        if (result == 0) {
            msg = "删除失败";
        } else {
            msg = "删除成功";
        }
        return JsonResult.success(msg);
    }
}
