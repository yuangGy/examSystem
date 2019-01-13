package com.gy.dao;

import com.gy.model.SysUserLog;

public interface SysUserLogMapper {
    int deleteByPrimaryKey(String sysUserLogId);

    int insert(SysUserLog record);

    int insertSelective(SysUserLog record);

    SysUserLog selectByPrimaryKey(String sysUserLogId);

    int updateByPrimaryKeySelective(SysUserLog record);

    int updateByPrimaryKey(SysUserLog record);
}