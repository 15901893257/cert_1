package com.bupt.dql.web.common;

import com.bupt.dql.common.util.DateUtil;
import com.bupt.dql.web.pojo.entity.SysUserDO;
import com.bupt.dql.web.pojo.vo.SysUserVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: mai
 * @date: 2020/9/22
 */
public class CommonAssistant {

    public static List<SysUserVO> transform(List<SysUserDO> sysUserDOList){
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
}
