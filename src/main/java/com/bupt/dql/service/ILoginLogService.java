package com.bupt.dql.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.LoginLogDO;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.query.LoginLogQuery;

/**
 * @author dengquanliang
 * Created on 2020/12/2
 */
public interface ILoginLogService extends IService<LoginLogDO> {
    /**
     * 根据查询条件获取数据列表
     *
     * @param query
     * @return
     */
    JsonResult getList(LoginLogQuery query);

    boolean insertOrUpdate(LoginLogDO loginLogDO);

    boolean batchInsert(List<LoginLogDO> loginLogDOList);

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
