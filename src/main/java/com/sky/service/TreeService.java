package com.sky.service;

import com.common.utils.LevelUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.sky.dto.DeptLeveDto;
import com.sky.entity.SysDept;
import com.sky.repository.DeptMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 部门层级树接口
 * Created by Administrator on 2018/8/29.
 */
@Service
public class TreeService {


    @Autowired
    private DeptMapper deptMapper;

    /**
     * 部门树列表
     * @return
     */
    public List<DeptLeveDto> deptTree(){

        List<SysDept> deptList= deptMapper.getAllDept();
        List<DeptLeveDto> dtoList = Lists.newArrayList();
        for (SysDept dept:deptList){
            DeptLeveDto dto = DeptLeveDto.adapt(dept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }


    /**
     * 部门树生成
     * 2018年9月10日15:13:58
     * @param deptLeveDtoList
     * @return
     */
    public  List<DeptLeveDto> deptListToTree(List<DeptLeveDto> deptLeveDtoList){
        if(CollectionUtils.isEmpty(deptLeveDtoList)){
            return Lists.newArrayList();
        }
        Multimap<String,DeptLeveDto> leveDeptMap = ArrayListMultimap.create();
        List<DeptLeveDto> rootList = Lists.newArrayList();
        for (DeptLeveDto dto : deptLeveDtoList){
            leveDeptMap.put(dto.getLevel(),dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }
         //seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLeveDto>() {
            @Override
            public int compare(DeptLeveDto o1, DeptLeveDto o2) {
                return o1.getSeq() -o2.getSeq();
            }
        });
        //递归成生成树
        transforDeptTree(rootList,LevelUtil.ROOT,leveDeptMap);
        return rootList;
    }

    /**
     * 递归调用
     * 2018年9月10日15:13:29
     * @param deptLeveDtoList
     * @param level:0, 0, all 0→0.1,0.2；
     *        level:0.1
     *        level:0.2
     * @param leveDeptMap
     *
     */
    public void transforDeptTree(List<DeptLeveDto> deptLeveDtoList,String level,Multimap<String,DeptLeveDto> leveDeptMap){
        for (int i=0; i<deptLeveDtoList.size();i++){
            //遍历该成每个元素
            DeptLeveDto deptLeveDto = deptLeveDtoList.get(i);
            //处理当前层级的数据
            String nextLevel = LevelUtil.caculateLevel(level,deptLeveDto.getId());
            //处理下层
            List<DeptLeveDto> tempDeptList = (List<DeptLeveDto>)leveDeptMap.get(nextLevel);
            if(CollectionUtils.isNotEmpty(tempDeptList)){
                //排序
                Collections.sort(tempDeptList,deptSeqDtoComparator);
                //设置下层部门
                deptLeveDto .setDeptList(tempDeptList);
                //进入下层处理
                transforDeptTree(tempDeptList,nextLevel,leveDeptMap);

            }
        }
    }

    public  Comparator<DeptLeveDto> deptSeqDtoComparator = new Comparator<DeptLeveDto>() {
        @Override
        public int compare(DeptLeveDto o1, DeptLeveDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };



}
