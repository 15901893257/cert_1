package com.bupt.dql.pojo.param;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Data
public class TestValidator {

    @Pattern(regexp = "deng|lee|yang")
    private String name;

    @Min(value = 5,message = "数量太小")
    private int num;

//    @Builder.Default
//    private int age = 1;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
