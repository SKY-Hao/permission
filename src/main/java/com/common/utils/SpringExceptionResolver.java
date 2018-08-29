package com.common.utils;

import com.common.exception.ParmException;
import com.common.exception.PermissionException;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sun.media.jfxmedia.logging.Logger.*;

/**
 * Created by Administrator on 2018/8/20.
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object o, Exception ex) {

        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System erro";

        //.json, .page
        //要求项目中，所有请求json数据，都使用.json结尾
        if (url.endsWith(".json")){
            if (ex instanceof PermissionException || ex instanceof ParmException){
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView",result.toMap());
            }else {
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView",result.toMap());
            }
        }else if (url.endsWith(".page")){
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception",result.toMap());
        }else {
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView",result.toMap());
        }

        return mv;
    }
}
