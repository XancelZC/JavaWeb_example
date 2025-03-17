package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpPageQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> pageQuery(EmpPageQueryDTO dto);

    //动态sql for-each实现
    void deleteEmps(List<Integer> ids);
}
