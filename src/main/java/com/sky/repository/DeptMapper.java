package com.sky.repository;

import com.sky.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门Mapper接口
 * 2018年8月29日17:09:18
 */
public interface DeptMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> getAllDept();

    List<SysDept> getChildDeptListBylevel(@Param("level") String level);

    void bacthUpdateLevel(@Param("deptList") List<SysDept> deptList);

    int countByNameParentId(@Param("parentId") Integer parentId,@Param("name") String name,@Param("id") Integer id);
}