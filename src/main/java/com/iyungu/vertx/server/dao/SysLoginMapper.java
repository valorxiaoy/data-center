package com.iyungu.vertx.server.dao;

import com.iyungu.vertx.server.bean.po.SysLogin;

public interface SysLoginMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLogin record);

    int insertSelective(SysLogin record);

    SysLogin selectByPrimaryKey(String id);

    SysLogin selectByLoginName(String loginName);

    int updateByPrimaryKeySelective(SysLogin record);

    int updateByPrimaryKey(SysLogin record);
}