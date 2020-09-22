package com.bupt.dql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.query.SysUserQuery;

import java.util.List;

public interface ISysUserService extends IService<SysUserDO> {

    /**
     * 根据查询条件获取数据列表
     *
     * @param query
     * @return
     */
    List<SysUserDO> queryUserList(SysUserQuery query);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUserDO querySysUserByUserName(String username);

    boolean insertOrUpdate(SysUserDO sysUserDO);

    boolean batchInsert(List<SysUserDO> sysUserDOList);
//    /**
//     * 批量插入
//     *
//     * @param sysUserDOList
//     * @return
//     */
//    boolean batchInsert(List<SysUserDO> sysUserDOList);
}
