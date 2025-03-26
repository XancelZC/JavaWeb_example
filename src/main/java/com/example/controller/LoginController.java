package com.example.controller;

import com.example.Utils.JwtUtil;
import com.example.pojo.Emp;
import com.example.pojo.LoginDTO;
import com.example.pojo.Result;
import com.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO){
        Emp emp = empService.findByUsername(loginDTO.getUsername());
        if(emp == null || !(emp.getPassword().equals(loginDTO.getPassword()))){
            return Result.error("用户名或密码错误");
        }

        String token = JwtUtil.generateToken(emp.getUsername(),emp.getId());

        return Result.success(token);
    }
}
