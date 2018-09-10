package com.sky.controller;

import com.common.utils.JsonData;
import com.sky.dto.DeptLeveDto;
import com.sky.param.DeptParam;
import com.sky.service.DeptService;
import com.sky.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 部门controller
 * Created by Administrator on 2018/8/29.
 */
@Controller
@RequestMapping("/sys/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private TreeService treeService;

    /**
     * 保存部门树
     * @param param
     * @return
     */
    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param){
        deptService.save(param);
        return JsonData.success();
    }

    /**
     * 部门树列表
     * @return
     */
    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree(){
        List<DeptLeveDto> dtoList = treeService.deptTree();
        return JsonData.success(dtoList);
    }

    /**
     * 更新部门树
     * @param param
     * @return
     */
    @RequestMapping("/uodate.json")
    @ResponseBody
    public JsonData updateDept(DeptParam param){
        deptService.updateTree(param);
        return JsonData.success();
    }




}
