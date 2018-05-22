package com.iyungu.vertx.main.service;

import com.iyungu.vertx.main.bean.po.SysLogin;

public interface ISysLoginService {

    SysLogin login(String id);

    boolean registered(SysLogin sysLogin);

    boolean bindUser(String loginId, String userId);

}
