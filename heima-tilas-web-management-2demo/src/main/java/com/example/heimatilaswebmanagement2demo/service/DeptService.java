package com.example.heimatilaswebmanagement2demo.service;

import com.example.heimatilaswebmanagement2demo.pojo.Dept;

import java.util.List;

//部门业务规则
public interface DeptService
{
    List<Dept> list();

    /**
     * 根据id删除部门
     * @param id    部门id
     */
    void delete(Integer id) throws Exception;

    /**
     * 新增部门
     * @param dept  部门对象
     */
    void add(Dept dept);



}
