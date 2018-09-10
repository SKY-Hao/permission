package com.common.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Created by Administrator on 2018/8/29.
 */
@Order(1)
@Aspect
@Component
public class HttpInterceptor extends HandlerInterceptorAdapter{

    private Logger  logger = Logger.getLogger(getClass());

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        Map par = request.getParameterMap();
        logger.info(url);
        logger.info(JsonMapper.obj2String(par));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

//        String url = request.getRequestURL().toString();
//        long start = (Long)request.getAttribute(START_TIME);
//        long end = System.currentTimeMillis();
//        logger.info(url);
//        logger.info(end - start);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        long start = (Long)request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        logger.info(url);
        logger.info(end - start);

    }
}
