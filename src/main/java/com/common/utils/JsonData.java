package com.common.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/16.
 */
@Getter
@Setter
@ToString
public class JsonData {


    private boolean ret;
    private String msg;
    private  Object data;

    public JsonData(boolean ret){
        this.ret=ret;
    }

    public static  JsonData success(Object object,String msg){
        JsonData jsonData = new JsonData(true);
        jsonData.data=object;
        jsonData.msg=msg;
        return jsonData;
    }

    public static  JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.data=object;
        return jsonData;
    }
    public static  JsonData success(){
        JsonData jsonData = new JsonData(true);
        return jsonData;
    }

    public static  JsonData fail(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.msg=msg;
        return jsonData;
    }

    public Map<String, Object> toMap(){
        HashMap<String , Object> result = new HashMap<String, Object>();
        result.put("ret",ret);
        result.put("msg",msg);
        result.put("data",data);
        return result;
    }


}
