package com.example.heimatilaswebmanagement2demo.controller;

import com.example.heimatilaswebmanagement2demo.pojo.Emp;
import com.example.heimatilaswebmanagement2demo.pojo.Result;
import com.example.heimatilaswebmanagement2demo.service.EmpService;
import com.example.heimatilaswebmanagement2demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//若登录成功，为用户生成jwt令牌并返回
@Slf4j
@RestController
public class LoginController
{
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp)
    {
        Emp loginEmp = empService.login(emp);
        if(loginEmp != null)
        {
            Map<String , Object> claims = new HashMap<>();
            claims.put("id", loginEmp.getId());
            claims.put("username",loginEmp.getUsername());
            claims.put("name",loginEmp.getName());
            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");

    }
}
