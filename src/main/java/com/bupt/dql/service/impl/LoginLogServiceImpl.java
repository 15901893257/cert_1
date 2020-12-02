package com.bupt.dql.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.dql.common.util.DateUtil;
import com.bupt.dql.dao.LoginLogMapper;
import com.bupt.dql.service.ILoginLogService;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.LoginLogDO;
import com.bupt.dql.web.pojo.vo.LoginLogVO;
import com.bupt.dql.web.query.LoginLogQuery;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLogDO> implements ILoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public JsonResult getList(LoginLogQuery query) {
        log.info("LoginLogQuery， query:{}", query);
        //查询条件
        QueryWrapper<LoginLogDO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(query.getUsername())) {
            queryWrapper.and(wrapper -> wrapper.like("username", query.getUsername()).or().eq("user_id", query.getUsername()));
        }
        if (query.getLoginType() != null && query.getLoginType() > -1) {
            queryWrapper.and(wrapper -> wrapper.eq("login_type", query.getLoginType()));
        }
        if (query.getResult() != null && query.getResult() > -1) {
            queryWrapper.and(wrapper -> wrapper.eq("result", query.getResult()));
        }
        queryWrapper.orderByDesc("login_time");

        IPage<LoginLogDO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<LoginLogDO> data = loginLogMapper.selectPage(page, queryWrapper);
        List<LoginLogDO> loginLogDOList = data.getRecords();
        if (CollectionUtils.isEmpty(loginLogDOList)) {
            return JsonResult.success();
        }
        List<LoginLogVO> loginLogVOList = loginLogDOList.stream().map(source -> {
            LoginLogVO target = new LoginLogVO();
            BeanUtils.copyProperties(source, target);
            target.setLoginTime(DateUtil.longToTime(source.getLoginTime()));
            return target;
        }).collect(Collectors.toList());
        return JsonResult.success("操作成功", loginLogVOList, data.getTotal());
    }

    @Override
    public int deleteById(Long id) {
        return loginLogMapper.deleteById(id);
    }

    @Override
    public int batchDelete(List<Long> ids) {
        return loginLogMapper.deleteBatchIds(ids);
    }

    @Override
    public boolean insertOrUpdate(LoginLogDO loginLogDO) {
        if (loginLogDO.getId() != null && loginLogDO.getId() > 0) {
            return this.updateById(loginLogDO);
        }
        return this.save(loginLogDO);
    }

    @Override
    public boolean batchInsert(List<LoginLogDO> loginLogDOList) {
        return this.saveBatch(loginLogDOList);
    }
}
