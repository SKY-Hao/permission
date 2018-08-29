package com.common.utils;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * Created by Administrator on 2018/8/28.
 */
public class JsonMapper {


    private  static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //config
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);

    }

    public static <T> String obj2String(T src){
        if (src ==  null){
            return null;
        }
        try {
            return src instanceof  String ? (String)src : objectMapper.writeValueAsString(src);
        }catch (Exception  ex){
            System.out.println("parse object to string exception"+ ex);
            return null;
        }
    }

    public static <T> T string2Obj(String src , TypeReference<T> tTypeReference){
        if (src ==null || tTypeReference ==null){
            return null;
        }
        try {
            return (T) (tTypeReference.getType().equals(String.class) ?src : objectMapper.readValue(src,tTypeReference));
        }catch (Exception ex){
            System.out.println("paese string to object exception,String:{},tTypeReference<T>:{},erro:{}" + src  + tTypeReference + ex);
        return null;
       }
    }





}
