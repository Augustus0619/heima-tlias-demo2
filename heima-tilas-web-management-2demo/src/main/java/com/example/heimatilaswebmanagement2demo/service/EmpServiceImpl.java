package com.example.heimatilaswebmanagement2demo.service;

import com.example.heimatilaswebmanagement2demo.anno.Log;
import com.example.heimatilaswebmanagement2demo.mapper.EmpMapper;
import com.example.heimatilaswebmanagement2demo.pojo.Emp;
import com.example.heimatilaswebmanagement2demo.pojo.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//员工业务实现类
@Slf4j
@Service
public class EmpServiceImpl implements EmpService
{
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end)
    {
//        //1.获取总页数
//        Long count = empMapper.count();
//
//        //2.获取分页查询结果列表
//        Integer start = (page - 1) * pageSize;
//        System.out.println(start);
//        List<Emp> empList = empMapper.list(start,pageSize);
//
//        //3.封装PageBean对象返回
//        return new PageBean(count,empList);

        // 设置分页参数
        PageHelper.startPage(page,pageSize);
        // 执行分页查询
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;

        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    @Log
    public void delete(List<Integer> ids)
    {
        empMapper.delete(ids);
    }

    @Override
    @Log
    public void insert(Emp emp)
    {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.findById(id);
    }

    @Override
    @Log
    public void update(Emp emp)
    {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp)
    {
        return empMapper.getByUsernameAndPwd(emp);
    }

}
