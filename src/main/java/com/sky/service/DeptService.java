package com.sky.service;

import com.common.exception.ParmException;
import com.common.utils.BeanValidator;
import com.common.utils.LevelUtil;
import com.google.common.base.Preconditions;
import com.sky.entity.SysDept;
import com.sky.param.DeptParam;
import com.sky.repository.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/29.
 */
@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 保存部门树
     * @param param
     */
    public void save(DeptParam param){
        BeanValidator.check(param);
        //判断信息是否满足要求
        if (checkExist(param.getParentId(),param.getName(),param.getId())){
            //如果部门相同 抛出异常
            throw new ParmException("同一层级下存在相同名称的部门");
        }

        SysDept dept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
            dept.setLevel(LevelUtil.caculateLevel(getLevel(param.getParentId()),param.getParentId()));
            dept.setOperator("system");//TODO
            dept.setOperatorIp("127.0.0.1");
            dept.setOperatorTime(new Date());
            deptMapper.insertSelective(dept);
    }


    /**
     * 更新部门树
     * @param param
     */
    public void updateTree(DeptParam param){
        BeanValidator.check(param);
        //判断信息是否满足要求
        if (checkExist(param.getParentId(),param.getName(),param.getId())){
            //如果部门相同 抛出异常
            throw new ParmException("同一层级下存在相同名称的部门");
        }
        //查询同一级部门在不在
        SysDept before = deptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的部门不存在");
        if (checkExist(param.getParentId(),param.getName(),param.getId())){
            //如果部门相同 抛出异常
            throw new ParmException("同一层级下存在相同名称的部门");
        }

        SysDept affter =SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
//TODO 未完成
    }


    //判断部门是否重复
    private  boolean checkExist(Integer parentId,String deptName,Integer deptId){

        //TODO
        return true;
    }

    private String getLevel(Integer deptId){
        SysDept dept = deptMapper.selectByPrimaryKey(deptId);
        if (dept == null){
            return null;
        }
        return dept.getLevel();
    }


}
