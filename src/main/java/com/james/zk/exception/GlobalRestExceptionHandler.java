package com.james.zk.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 捕获异常统一处理
 * @author chen.gs
 * @create date 2016年4月28日
 * @modified by
 * @modify date
 * @version v1.0
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    private final static String EXPTION_MSG_KEY = "message";

    @ExceptionHandler(Exception.class)
    public Exception handleBizExp(HttpServletRequest request, Exception ex){
//        LOG.info("Business exception handler  " + ex.getMessage() );
        return ex;
    }
}
