package com.bupt.dql.web.handle;

import com.bupt.dql.web.exception.BaseException;
import com.bupt.dql.web.common.JsonResult;
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
    public JsonResult exceptionHandler(BaseException e) {
        return JsonResult.error(e.getMessage());
    }
}
