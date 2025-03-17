package com.example.controller;

import com.example.pojo.EmpPageQueryDTO;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result pageQuery(EmpPageQueryDTO dto){
        log.info("分页查询：第{}页,每页{}个",dto.getPage(),dto.getPageSize());
        PageResult pageResult = empService.pageQuery(dto);
        return Result.success(pageResult);
    }
}
