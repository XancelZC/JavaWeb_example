package com.example.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpPageQueryDTO {  //query意为查询
    private Integer page = 1;      // 默认第1页
    private Integer pageSize = 10; // 默认每页10条
    private String name;           // 员工姓名（模糊查询）
    private Integer gender;        // 性别（1男 2女）
    private Integer deptId;        // 所属部门ID
    private LocalDate begin;       //查询的起始时间
    private LocalDate end;          //查询的结束时间
}
