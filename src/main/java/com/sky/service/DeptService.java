package com.sky.service;

import com.common.exception.ParmException;
import com.common.utils.BeanValidator;
import com.sky.entity.SysDept;
import com.sky.param.DeptParam;
import com.sky.repository.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/29.
 */
@Service
public class DeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;


    public void save(DeptParam param){
        BeanValidator.check(param);
        //判断信息是否满足要求
        if (checkExist(param.getParentId(),param.getName(),param.getId())){
            //如果部门相同 抛出异常
            throw new ParmException("同一层级下存在相同名称的部门");
        }

        SysDept.builder;

    }


    //判断部门是否重复
    private  boolean checkExist(Integer parentId,String deptName,Integer deptId){

        //TODO
        return true;
    }


}
