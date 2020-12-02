package com.bupt.dql.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.SensitiveWordsDO;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.query.SensitiveWordsQuery;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
public interface ISensitiveService extends IService<SensitiveWordsDO> {

    /**
     * 根据查询条件获取数据列表
     *
     * @param query
     * @return
     */
    JsonResult getList(SensitiveWordsQuery query);

    boolean insertOrUpdate(SensitiveWordsDO sensitiveWordsDO);

    boolean batchInsert(List<SensitiveWordsDO> sensitiveWordsDOList);

    SensitiveWordsDO queryById(Long id);

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
