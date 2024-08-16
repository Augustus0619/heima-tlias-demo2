package com.example.heimatilaswebmanagement2demo.mapper;

import com.example.heimatilaswebmanagement2demo.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper
{
    @Insert("INSERT INTO dept_log (create_time,description) values (#{createTime},#{description})")
    public void insert(DeptLog deptLog);
}
