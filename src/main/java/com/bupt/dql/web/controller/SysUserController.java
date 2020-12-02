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
import com.bupt.dql.service.ISysUserService;
import com.bupt.dql.web.common.BaseController;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.vo.SysUserVO;
import com.bupt.dql.web.query.SysUserQuery;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Slf4j
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService sysUserService;

    @PostMapping("/list")
    @ResponseBody
    public JsonResult list(SysUserQuery query) {
        return sysUserService.getList(query);
    }

    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody SysUserVO sysUserVO, HttpServletRequest request) throws ParseException {
        log.info("注册用户，sysUserVO: {}", sysUserVO);
        //存入会话session
        HttpSession session = request.getSession();
        String operator = (String) session.getAttribute("username");
        SysUserDO sysUserDO = getSysUserDO(sysUserVO, operator);

        sysUserService.insertOrUpdate(sysUserDO);
        return JsonResult.success("新增用户成功");
    }


    @Override
    public String edit(Long id, Model model) {
        log.info("根据id查询user,id: {}", id);
        SysUserVO sysUserVO = new SysUserVO();
        if (id != null && id > 0) {
            SysUserDO sysUserDO = sysUserService.queryById(id);
            BeanUtils.copyProperties(sysUserDO, sysUserVO);
            sysUserVO.setBirthDay(DateUtil.longToDay(sysUserDO.getBirthDay()));
            model.addAttribute("info", sysUserVO);
        }
        return super.edit(id, model);
    }

    /**
     * 更新记录
     * @param sysUserVO
     * @param request
     * @return
     * @throws ParseException
     */
    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestBody SysUserVO sysUserVO, HttpServletRequest request) throws ParseException {
        log.info("更新用户，sysUserVO: {}", sysUserVO);
        HttpSession session = request.getSession();
        String operator = (String) session.getAttribute("username");
        SysUserDO sysUserDO = getSysUserDO(sysUserVO, operator);

        sysUserService.insertOrUpdate(sysUserDO);
        return JsonResult.success("更新用户成功");
    }

    /**
     * 删除记录
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public JsonResult delete(@PathVariable("id") Long id) {
        log.info("删除用户，id:{}", id);
        int result = sysUserService.deleteById(id);
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
        log.info("批量删除用户，ids:{}", ids);
        int result = sysUserService.batchDelete(ids);
        String msg = "";
        if (result == 0) {
            msg = "删除失败";
        } else {
            msg = "删除成功";
        }
        return JsonResult.success(msg);
    }

    private SysUserDO getSysUserDO(SysUserVO sysUserVO, String operator) throws ParseException {
        SysUserDO sysUserDO = new SysUserDO();
        BeanUtils.copyProperties(sysUserVO, sysUserDO, "password");

        sysUserDO.setUserId(sysUserService.getUserId());
        sysUserDO.setPassword(MD5Util.encrypt(sysUserVO.getPassword()));
        sysUserDO.setOperator(operator);
        sysUserDO.setUpdateOperator(operator);
        sysUserDO.setLoginIp("127.0.0.1");
        long curTime = System.currentTimeMillis();
        sysUserDO.setUtime(curTime);
        sysUserDO.setCtime(curTime);
        sysUserDO.setUType(0);
        sysUserDO.setBirthDay(DateUtil.stringToLong(sysUserVO.getBirthDay()));
        return sysUserDO;
    }

    /**
     * 测试
     * @return
     */
    @GetMapping("/test")
    public String index() {
        return "sysUser/edit";
    }
}
