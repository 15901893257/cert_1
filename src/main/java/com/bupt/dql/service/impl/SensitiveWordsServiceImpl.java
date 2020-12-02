package com.bupt.dql.service.impl;

import java.util.ArrayList;
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
import com.bupt.dql.dao.SensitiveWordsMapper;
import com.bupt.dql.service.ISensitiveService;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.SensitiveWordsDO;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.vo.SensitiveWordsVO;
import com.bupt.dql.web.query.SensitiveWordsQuery;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
@Slf4j
@Service
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsMapper, SensitiveWordsDO> implements ISensitiveService {

    @Resource
    private SensitiveWordsMapper sensitiveWordsMapper;

    @Override
    public JsonResult getList(SensitiveWordsQuery query) {
        log.info("SensitiveWordsQuery查询条件， query:{}", query);
        //查询条件
        QueryWrapper<SensitiveWordsDO> queryWrapper = new QueryWrapper<>();
        if (query.getKeyType() != null) {
            queryWrapper.and(wrapper -> wrapper.eq("key_type", query.getKeyType()));
        }
        if (query.getIndexType() != null) {
            queryWrapper.and(wrapper -> wrapper.eq("index_type", query.getIndexType()));
        }
        if (StringUtils.isNotEmpty(query.getOperator())) {
            queryWrapper.and(wrapper -> wrapper.like("operator", query.getOperator()));
        }
        queryWrapper.orderByAsc("id");

        //查询数据
        IPage<SensitiveWordsDO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<SensitiveWordsDO> data = sensitiveWordsMapper.selectPage(page, queryWrapper);
        List<SensitiveWordsDO> sensitiveWordsDOList = data.getRecords();
        List<SensitiveWordsVO> sensitiveWordsVOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sensitiveWordsDOList)) {
            return JsonResult.success();
        }
        sensitiveWordsVOList = transform(sensitiveWordsDOList);
        return JsonResult.success("查询成功", sensitiveWordsVOList, data.getTotal());
    }

    @Override
    public boolean insertOrUpdate(SensitiveWordsDO sensitiveWordsDO) {
        if (sensitiveWordsDO.getId() != null && sensitiveWordsDO.getId() > 0) {
            //修改
            return this.updateById(sensitiveWordsDO);
        }
        return this.save(sensitiveWordsDO);
    }

    @Override
    public boolean batchInsert(List<SensitiveWordsDO> sensitiveWordsDOList) {
        return this.saveBatch(sensitiveWordsDOList);
    }

    @Override
    public SensitiveWordsDO queryById(Long id) {
        return sensitiveWordsMapper.selectById(id);
    }

    @Override
    public int deleteById(Long id) {
        return sensitiveWordsMapper.deleteById(id);
    }

    @Override
    public int batchDelete(List<Long> ids) {
        return sensitiveWordsMapper.deleteBatchIds(ids);
    }

    private List<SensitiveWordsVO> transform(List<SensitiveWordsDO> sensitiveWordsDOList) {
        List<SensitiveWordsVO> sensitiveWordsVOList = sensitiveWordsDOList.stream().map(source -> {
            SensitiveWordsVO target = new SensitiveWordsVO();
            BeanUtils.copyProperties(source, target);
            target.setCtime(DateUtil.longToTime(source.getCtime()));
            target.setUtime(DateUtil.longToTime(source.getUtime()));
            return target;
        }).collect(Collectors.toList());
        return sensitiveWordsVOList;
    }
}
