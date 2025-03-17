package com.example.service;

import com.example.pojo.EmpPageQueryDTO;
import com.example.pojo.PageResult;

import java.util.List;


public interface EmpService {
    PageResult pageQuery(EmpPageQueryDTO dto);

    void deleteEmps(List<Integer> ids);
}
