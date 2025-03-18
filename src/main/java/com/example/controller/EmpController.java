package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.EmpPageQueryDTO;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{ids}")
    public Result deleteEmps(@PathVariable List<Integer> ids){
        log.info("删除id为{}的员工",ids);
        empService.deleteEmps(ids);
        return Result.success();
    }

    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("新增员工{}",emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("查询id为{}的员工",id);
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result changeEmp(@RequestBody Emp emp){
        log.info("修改员工:{}信息操作",emp);
        empService.changeEmp(emp);
        return Result.success();
    }
}
