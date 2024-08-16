package com.example.heimatilaswebmanagement2demo.service;

import com.example.heimatilaswebmanagement2demo.pojo.Emp;
import com.example.heimatilaswebmanagement2demo.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//员工业务规则
public interface EmpService
{
    /**
     * 条件分页查询
     * @param page 页码
     * @param pageSize 每页展示记录数
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin,LocalDate end);

    /**
     * 批量删除操作
     * @param ids id集合
     */
    void delete(List<Integer> ids);

    void insert(Emp emp);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);



}
