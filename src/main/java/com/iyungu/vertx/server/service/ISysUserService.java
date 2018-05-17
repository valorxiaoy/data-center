package com.iyungu.vertx.server.service;

import com.iyungu.vertx.server.bean.po.SysUser;

public interface ISysUserService {

    SysUser selectById(String id);

    boolean registered(SysUser sysUser);
}
