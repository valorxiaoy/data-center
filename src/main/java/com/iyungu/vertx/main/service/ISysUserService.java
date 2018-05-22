package com.iyungu.vertx.main.service;

import com.iyungu.vertx.main.bean.po.SysUser;

public interface ISysUserService {

    SysUser selectById(String id);

    SysUser registered(SysUser sysUser);
}
