package com.bupt.dql.pojo.param;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
public class SearchParam {
    private String projectName;             //项目名
    private String authorName;              //作者名

    private String platForm;                //平台

    private String language;                //语言
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date from;                      //最后更新时间段-起
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date to;                        //最后更新时间段-止

    private Integer starNumberFrom;                 //star星数-起
    private Integer state;

    private Integer starNumberTo;     //star星数-止

    private String sortParam;         //排序字段

    private String orderType;                         //排序规则（DESC/ASC）

    private int pageSize ;                                 //页面大小

    private int pageNo ;                                   //页码

    private int sortModel;
}
