package com.bupt.dql.pojo.param;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Data
public class BaseInfoParam {

    private String cveId;//CVE-ID
    private String bugType;//漏洞类型
    private String languageType;//语言类型
    private String dangerLevel;//漏洞危险级别
    private String origin;//漏洞来源
    private String status;//漏洞状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date from;                      //漏洞发布时间段-起
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date to;                        //漏洞发布时间段-止


}
