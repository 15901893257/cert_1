package com.bupt.dql.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.dql.web.pojo.entity.SysUserDO;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUserDO> {
}
