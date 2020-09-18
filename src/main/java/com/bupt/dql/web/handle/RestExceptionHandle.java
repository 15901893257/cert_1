package com.bupt.dql.web.handle;

import com.bupt.dql.web.exception.BaseException;
import com.bupt.dql.web.pojo.dto.ResultDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: mai
 * @date: 2020/9/14
 */
@ControllerAdvice
public class RestExceptionHandle {

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResultDTO exceptionHandler(BaseException e) {
        return ResultDTO.fail(e.getMessage());
    }
}
