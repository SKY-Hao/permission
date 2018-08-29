package com.sky.controller;import com.common.exception.ParmException;import com.common.utils.ApplicationContextHelper;import com.common.utils.BeanValidator;import com.common.utils.JsonData;import com.sky.entity.SysAclModule;import com.sky.param.TestVo;import com.sky.repository.SysAclModuleMapper;import com.sun.javafx.image.PixelUtils;import com.sun.media.jfxmedia.logging.Logger;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;import java.util.Map;/** * Created by Administrator on 2018/8/16. */@Controller@RequestMapping("/test")public class TestController {    @RequestMapping("/hello.json")    @ResponseBody    public JsonData hello(){       return JsonData.success("hello sky");    }    @RequestMapping("/valitate.json")    @ResponseBody    public JsonData valitate(TestVo vo) throws ParmException{        SysAclModuleMapper moduleMapper =                ApplicationContextHelper.popBen(SysAclModuleMapper.class);        SysAclModule module = moduleMapper.selectByPrimaryKey(1);        BeanValidator.check(vo);        return JsonData.success("hello sky");    }}