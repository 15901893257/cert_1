package com.bupt.dql.web.pojo.vo;

import lombok.Data;

/**
 * @author: mai
 * @date: 2020/9/16
 */
@Data
public class ElasticCodeVO {

    private String project;

    private String filename;

    private String user_filename;

    private String code;

    private String highlight;

    private String language;
}
