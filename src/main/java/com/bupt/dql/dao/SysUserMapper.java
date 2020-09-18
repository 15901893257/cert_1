package com.bupt.dql.dao;

import com.bupt.dql.web.pojo.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    public SysUserDO getSysUser(String username);
}
