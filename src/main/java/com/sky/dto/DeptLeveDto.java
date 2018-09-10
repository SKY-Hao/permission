package com.sky.dto;

import com.google.common.collect.Lists;
import com.sky.entity.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 部门dto
 * Created by Administrator on 2018/8/29.
 */
@Setter
@Getter
@ToString
public class DeptLeveDto extends SysDept {

    private List<DeptLeveDto> deptList= Lists.newArrayList();

    public  static  DeptLeveDto adapt(SysDept dept){
        DeptLeveDto dto = new DeptLeveDto();
        BeanUtils.copyProperties(dept,dto);

        return dto;
    }



}
