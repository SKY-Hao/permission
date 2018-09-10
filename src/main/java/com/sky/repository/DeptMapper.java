package com.sky.repository;

import com.sky.entity.SysDept;

import java.util.List;

/**
 * 部门Mapper接口
 * 2018年8月29日17:09:18
 */
public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> getAllDept();
}