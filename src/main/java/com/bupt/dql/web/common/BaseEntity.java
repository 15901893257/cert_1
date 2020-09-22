package com.bupt.dql.web.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 基类实体对象
 *
 * @author: mai
 * @date: 2020/9/22
 */
@Data
public class BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *  创建时间
     */
    private Long ctime;

    /**
     *  更新时间
     */
    private Long utime;

    /**
     *  创建人
     */
    private String operator;

    /**
     *  备注
     */
    private String remark;
}
