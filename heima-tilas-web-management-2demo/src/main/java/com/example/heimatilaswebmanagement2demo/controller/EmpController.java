package com.example.heimatilaswebmanagement2demo.controller;

import com.example.heimatilaswebmanagement2demo.pojo.Emp;
import com.example.heimatilaswebmanagement2demo.pojo.PageBean;
import com.example.heimatilaswebmanagement2demo.pojo.Result;
import com.example.heimatilaswebmanagement2demo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController
{
    @Autowired
    private EmpService empService;

//    //分页查询
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize)
//    {
//        log.info("分页查询,参数:{},{}",page,pageSize);
//        PageBean pageBean = empService.page(page,pageSize);
//        return Result.success(pageBean);
//    }

    //条件分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end)
    {
        //记录日志
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize,name, gender, begin, end);
        //调用业务层分页查询功能
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    //批量删除
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids)
    {
        log.info("批量删除,参数{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    //新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp)
    {
        log.info("新增员工,emp:{}",emp);
        empService.insert(emp);
        return Result.success();
    }

    //根据id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp)
    {
        log.info("修改员工,emp:{}",emp);
        empService.update(emp);
        return Result.success();
    }


}
