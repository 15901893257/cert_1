package com.bupt.dql.pojo.elasticsearch;

import lombok.Data;

/**
 * @author: mai
 * @date: 2019/7/3
 */
@Data
public class Coding {

    private String project;

    private String filename;

    private String user_filename;

    private String code;

    private String highlight;

    private String language;
}
