package com.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/8/29.
 */
public class LevelUtil {




    public final  static  String SEPARATOR =".";

    public final  static String ROOT="0";
    public static  String caculateLevel(String parentLevel,int parentId){
        if (StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else {
            return  StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }


}
