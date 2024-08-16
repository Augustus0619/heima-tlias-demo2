package com.example.heimatilaswebmanagement2demo.mapper;

import com.example.heimatilaswebmanagement2demo.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper
{
    @Select("select count(*) from emp")
    Long count();

//    @Select("select * from emp limit #{start},#{pageSize}")
//    List<Emp> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    List<Emp> list(@Param("name") String name, @Param("gender") Short gender,@Param("begin") LocalDate begin,
                   @Param("end") LocalDate end);

    void delete(@Param("ids") List<Integer> ids);

    @Insert("insert into emp (username,name,gender,image,job,entrydate,dept_id,create_time,update_time)" +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);

    //根据ID查询员工信息
    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time " +
            "from emp " +
            "where id = #{id}")
    Emp findById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPwd(Emp emp);

    @Delete("delete from emp where dept_id = #{dept_id}")
    void deleteByDeptId(Integer dept_id);


}
