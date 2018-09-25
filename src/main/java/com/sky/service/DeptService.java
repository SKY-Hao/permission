package com.sky.service;

import com.common.exception.ParmException;
import com.common.utils.BeanValidator;
import com.common.utils.LevelUtil;
import com.google.common.base.Preconditions;
import com.sky.entity.SysDept;
import com.sky.param.DeptParam;
import com.sky.repository.DeptMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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

        SysDept affter =SysDept.builder().id(param.getId()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        affter.setLevel(LevelUtil.caculateLevel(getLevel(param.getParentId()),param.getParentId()));
        affter.setOperator("system-update");//TODO
        affter.setOperatorIp("127.0.0.1");
        affter.setOperatorTime(new Date());

        updateWithChild(before,affter);

    }

    /**
     * 部门更新
     * @param befor
     * @param after
     */
    @Transactional
    private void  updateWithChild(SysDept befor,SysDept after){

        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = befor.getLevel();
        if (!after.getLevel().equals(befor.getLevel())){
            //查询当前部门的子部门
            List<SysDept> deptList = deptMapper.getChildDeptListBylevel(befor.getLevel());
            if(CollectionUtils.isNotEmpty(deptList)){
                for (SysDept dept :deptList){
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix)==0){
                        level= newLevelPrefix + level.substring(oldLevelPrefix.length());
                    }
                }
                deptMapper.bacthUpdateLevel(deptList);
            }
        }
        deptMapper.updateByPrimaryKey(after);

    }


    //判断部门是否重复
    private  boolean checkExist(Integer parentId,String deptName,Integer deptId){

        //TODO
        return deptMapper.countByNameParentId(parentId,deptName,deptId)>0;
    }

    private String getLevel(Integer deptId){
        SysDept dept = deptMapper.selectByPrimaryKey(deptId);
        if (dept == null){
            return null;
        }
        return dept.getLevel();
    }


}
