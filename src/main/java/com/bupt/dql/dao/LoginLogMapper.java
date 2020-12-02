package com.bupt.dql.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.dql.web.pojo.entity.LoginLogDO;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLogDO> {
}
