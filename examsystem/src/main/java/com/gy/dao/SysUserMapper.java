package com.gy.dao;

import org.apache.ibatis.annotations.Param;

import com.gy.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer sysUserId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer sysUserId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser findByNameAndPwd(@Param("sysUserName")String username, @Param("sysUserPassword")String p);
}