package com.bupt.dql.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.SensitiveWordsDO;
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
}
