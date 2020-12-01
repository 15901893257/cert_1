package com.bupt.dql.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.dql.web.pojo.entity.SensitiveWordsDO;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
@Mapper
public interface SensitiveWordsMapper extends BaseMapper<SensitiveWordsDO> {
}
