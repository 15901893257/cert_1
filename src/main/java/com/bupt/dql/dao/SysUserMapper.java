package com.bupt.dql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUserDO> {

//    public SysUserDO getSysUser(String username);
}
