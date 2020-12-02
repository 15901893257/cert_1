package com.bupt.dql.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.dql.service.ILoginLogService;
import com.bupt.dql.web.common.BaseController;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.query.LoginLogQuery;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
@Slf4j
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    @Resource
    private ILoginLogService loginLogService;

    @PostMapping("/list")
    @ResponseBody
    public JsonResult list(LoginLogQuery query) {
        return loginLogService.getList(query);
    }

    /**
     * 删除记录
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public JsonResult delete(@PathVariable("id") Long id) {
        log.info("删除日志，id:{}", id);
        int result = loginLogService.deleteById(id);
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
        log.info("批量删除日志，ids:{}", ids);
        int result = loginLogService.batchDelete(ids);
        String msg = "";
        if (result == 0) {
            msg = "删除失败";
        } else {
            msg = "删除成功";
        }
        return JsonResult.success(msg);
    }
}
