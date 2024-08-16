package com.example.heimatilaswebmanagement2demo.service;

import com.example.heimatilaswebmanagement2demo.anno.Log;
import com.example.heimatilaswebmanagement2demo.mapper.DeptMapper;
import com.example.heimatilaswebmanagement2demo.mapper.EmpMapper;
import com.example.heimatilaswebmanagement2demo.pojo.Dept;
import com.example.heimatilaswebmanagement2demo.pojo.DeptLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//部门业务实现类
@Slf4j
@Service
public class DeptServiceImpl implements DeptService
{
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list()
    {
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }

    @Override
    @Log
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception
    {
        try
        {
            deptMapper.deleteById(id);
            //模拟：异常
            if(true){
                throw new Exception("出现异常了~~~");
            }
            empMapper.deleteByDeptId(id);
        }
            finally
            {
                //不论是否有异常，最终都要执行的代码：记录日志
                DeptLog deptLog = new DeptLog();
                deptLog.setCreateTime(LocalDateTime.now());
                deptLog.setDescription("执行了解散部门的操作，此时解散的是"+id+"号部门");
                //调用其他业务类中的方法
                deptLogService.insert(deptLog);
        }
    }

    @Override
    @Log
    public void add(Dept dept)
    {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
}
