package com.bupt.dql.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.bupt.dql.common.util.DateUtil;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.vo.SysUserVO;

/**
 * @author dengquanliang
 * Created on 2020/11/30
 */
public class SysUserServiceUtil {
    public static List<SysUserVO> transform(List<SysUserDO> sysUserDOList) {
        List<SysUserVO> sysUserVOList = new ArrayList<>();
        sysUserVOList = sysUserDOList.stream().map(source -> {
            SysUserVO target = new SysUserVO();
            BeanUtils.copyProperties(source, target);
            target.setBirthDay(String.valueOf(DateUtil.longToDay(source.getBirthDay())));
            target.setCtime(String.valueOf(DateUtil.longToTime(source.getCtime())));
            target.setUtime(String.valueOf(DateUtil.longToTime(source.getUtime())));
            target.setLoginTime(String.valueOf(DateUtil.longToTime(source.getLoginTime())));
            return target;
        }).collect(Collectors.toList());
        return sysUserVOList;
    }

    public static SysUserDO build(SysUserVO sysUserVO) {
        SysUserDO sysUserDO = new SysUserDO();
        BeanUtils.copyProperties(sysUserVO, sysUserDO);
        long curTime = System.currentTimeMillis();

        return sysUserDO;
    }
}
