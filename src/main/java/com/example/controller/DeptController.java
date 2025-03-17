package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController//这个注解就相当于ResponseBody + Controller
@RequestMapping("/depts") //这个注解可以统一为该类添加一个请求头
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result getDeptList(){
        log.info("查询全部部门信息");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id){  //@PathVariable 将上面的路径变量绑定到id上
        log.info("删除id为:{}的部门",id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        log.info("新增部门 ");
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id){
        log.info("查询id为：{}的部门",id);
        Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result changeById(@RequestBody Dept dept){
        log.info("修改id为{}的部门",dept.getId());
        deptService.changeById(dept);
        return Result.success();
    }
}
