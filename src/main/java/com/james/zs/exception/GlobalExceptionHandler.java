package com.james.zs.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @description 捕获异常统一处理
 * @author chen.gs
 * @create date 2016年4月28日
 * @modified by
 * @modify date
 * @version v1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String EXPTION_MSG_KEY = "message";



    @ExceptionHandler(Exception.class)
    public ModelAndView handleBizExp(HttpServletRequest request, Exception ex){
//        LOG.info("Business exception handler  " + ex.getMessage() );
        System.out.println(request.getHeader("Accept"));
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName("errorPage");
        return mv;
    }
}
