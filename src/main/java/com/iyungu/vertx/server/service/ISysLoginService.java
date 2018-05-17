package com.iyungu.vertx.server.service;

import com.iyungu.vertx.server.bean.po.SysLogin;

public interface ISysLoginService {

    SysLogin login(String id);

    boolean registered(SysLogin sysLogin);

}
