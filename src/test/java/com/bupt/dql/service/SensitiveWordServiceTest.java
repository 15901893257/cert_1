package com.bupt.dql.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.bupt.dql.TestBase;
import com.bupt.dql.web.enums.ElasticSearchEnum;
import com.bupt.dql.web.enums.IndexEnum;
import com.bupt.dql.web.enums.StatusEnum;
import com.bupt.dql.web.pojo.entity.SensitiveWordsDO;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
public class SensitiveWordServiceTest extends TestBase {

    @Resource
    private ISensitiveService sensitiveService;

    @Test
    public void testBatchInsert() {
        List<SensitiveWordsDO> sensitiveWordsDOList = new ArrayList<>();
        long cur = System.currentTimeMillis();
        for (int i = 0; i < 40; i++) {
            SensitiveWordsDO sensitiveWordsDO = SensitiveWordsDO.builder()
                    .keyword("include")
                    .keyType(ElasticSearchEnum.MATCH_ALL.getCode())
                    .indexType(IndexEnum.C.getCode())
                    .description("测试")
                    .operator("mai")
                    .ctime(cur)
                    .utime(cur)
                    .status(StatusEnum.VALID.getCode())
                    .build();
            sensitiveWordsDOList.add(sensitiveWordsDO);
        }
        sensitiveService.batchInsert(sensitiveWordsDOList);
    }
}
