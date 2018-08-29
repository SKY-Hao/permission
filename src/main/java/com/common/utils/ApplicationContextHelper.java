package com.common.utils;

import com.sky.repository.SysAclModuleMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/28.
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper  implements ApplicationContextAware{


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {

        applicationContext = context;
    }

    public static <T> T popBen(Class<T> clazz){
        if (applicationContext ==null){
            return null;
        }
        return  applicationContext.getBean(clazz);
    }

    public  static  <T> T popBean(String name,Class<T> clazz){
        if (applicationContext ==null){
            return null;
        }
        return applicationContext.getBean(name,clazz);
    }

}
