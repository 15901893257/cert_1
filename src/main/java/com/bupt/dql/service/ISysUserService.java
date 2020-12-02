package com.bupt.dql.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.query.SysUserQuery;

public interface ISysUserService extends IService<SysUserDO> {

    /**
     * 根据查询条件获取数据列表
     *
     * @param query
     * @return
     */
    List<SysUserDO> queryList(SysUserQuery query);

    /**
     * 根据查询条件获取数据列表
     *
     * @param query
     * @return
     */
     JsonResult getList(SysUserQuery query);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUserDO querySysUserByUserName(String username);

    boolean insertOrUpdate(SysUserDO sysUserDO);

    boolean batchInsert(List<SysUserDO> sysUserDOList);

    /**
     * 返回新增user_id
     * @return
     */
    long getUserId();

    SysUserDO queryById(Long id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(List<Long> ids);

}
