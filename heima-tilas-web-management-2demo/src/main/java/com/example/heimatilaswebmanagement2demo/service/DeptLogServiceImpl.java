package com.example.heimatilaswebmanagement2demo.service;

import com.example.heimatilaswebmanagement2demo.mapper.DeptLogMapper;
import com.example.heimatilaswebmanagement2demo.pojo.DeptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService
{
    @Autowired
    private DeptLogMapper deptLogMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(DeptLog deptLog)
    {
        deptLogMapper.insert(deptLog);
    }
}
