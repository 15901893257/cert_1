package com.bupt.dql.web.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dengquanliang
 * Created on 2020/12/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sensitive_words")
public class SensitiveWordsDO implements Serializable {

    private static final long serialVersionUID = 2534114438484301779L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 0-模糊匹配 1-正则表达式
     */
    private Integer keyType;

    /**
     * 语言
     *  -1-all 0-c 1-c++ 2-c# 3-java 4-javascript 5-python 6-ruby
     */
    private Integer indexType;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人或更新人
     */
    private String operator;

    /**
     * 创建时间
     */
    private Long ctime;

    /**
     * 更新时间
     */
    private Long utime;

    /**
     * 状态,0-有效 1-无效
     */
    private Integer status;
}
