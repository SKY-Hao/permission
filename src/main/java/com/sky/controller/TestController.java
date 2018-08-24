package com.sky.controller;

import com.sun.javafx.image.PixelUtils;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/8/16.
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){

       return "hello sky";
    }

}
