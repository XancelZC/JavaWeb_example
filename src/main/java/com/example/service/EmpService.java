package com.example.service;

import com.example.pojo.EmpPageQueryDTO;
import com.example.pojo.PageResult;


public interface EmpService {
    PageResult pageQuery(EmpPageQueryDTO dto);
}
