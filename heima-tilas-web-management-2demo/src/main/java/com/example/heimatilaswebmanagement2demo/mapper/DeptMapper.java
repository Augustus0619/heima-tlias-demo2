package com.example.heimatilaswebmanagement2demo.mapper;

import com.example.heimatilaswebmanagement2demo.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper
{
    @Select("select * from dept")
    //查询所有部门数据
    List<Dept> list();

    /**
     * 根据id删除部门信息
     * @param id   部门id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    @Insert("INSERT INTO dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
}
