package com.bupt.dql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.dql.dao.SysUserMapper;
import com.bupt.dql.service.ISysUserService;
import com.bupt.dql.web.common.CommonAssistant;
import com.bupt.dql.web.common.JsonResult;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.vo.SysUserVO;
import com.bupt.dql.web.query.SysUserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: mai
 * @date: 2020/9/22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements ISysUserService{

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public JsonResult getList(SysUserQuery query) {
        //查询条件
        QueryWrapper<SysUserDO> queryWrapper = new QueryWrapper<>();
        // 姓名/用户名/手机号
        if (!StringUtils.isEmpty(query.getKeywords())) {
            queryWrapper.like("name", query.getKeywords())
                    .or().like("username", query.getKeywords())
                    .or().like("phone", query.getKeywords());
        }
        // 性别:0女 1男
        if (query.getGender() != null) {
            queryWrapper.eq("gender", query.getGender());
        }
        // 状态：0正常 1禁用
        if (query.getStatus() != null) {
            queryWrapper.eq("status", query.getStatus());
        }
        //queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("id");

        //查询数据
        IPage<SysUserDO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<SysUserDO> data = sysUserMapper.selectPage(page, queryWrapper);
        List<SysUserDO> sysUserDOList = data.getRecords();
        List<SysUserVO> sysUserVOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sysUserDOList)) {
            return JsonResult.success();
        }
        //数据封装
        sysUserVOList = CommonAssistant.transform(sysUserDOList);
        return JsonResult.success("操作成功", sysUserVOList, data.getTotal());
    }

    /**
     * 根据用户名获取人员
     *
     * @param username 用户名
     * @return
     */
    @Override
    public SysUserDO querySysUserByUserName(String username) {
        //查询条件
        QueryWrapper<SysUserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SysUserDO sysUserDO = sysUserMapper.selectOne(queryWrapper);
        return sysUserDO;
    }

    @Override
    public boolean insertOrUpdate(SysUserDO sysUserDO) {
        if (sysUserDO.getId() != null && sysUserDO.getId() > 0) {
            //修改
            return this.updateById(sysUserDO);
        } else {
            //新增
            return this.save(sysUserDO);
        }
    }

    @Override
    public boolean batchInsert(List<SysUserDO> sysUserDOList) {
        return this.saveBatch(sysUserDOList);
    }


//    @Override
//    public boolean batchInsert(List<SysUserDO> sysUserDOList) {
//        return this.saveBatch(sysUserDOList);
//    }

}
