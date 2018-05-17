package com.iyungu.vertx.server.service.impl;

import com.iyungu.vertx.config.MyBatisUtil;
import com.iyungu.vertx.server.bean.po.SysLogin;
import com.iyungu.vertx.server.dao.SysLoginMapper;
import com.iyungu.vertx.server.service.ISysLoginService;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 10:26$ 2018/5/16$
 * @Modified By:
 */
public class SysLoginServiceImpl implements ISysLoginService {

    private MyBatisUtil myBatisUtil = new MyBatisUtil();

    public SysLogin login(String loginName) {
        SysLoginMapper sysLoginMapper = myBatisUtil.getMapper(SysLoginMapper.class);
        SysLogin sysLogin = sysLoginMapper.selectByLoginName(loginName);
        return sysLogin;
    }

    public boolean registered(SysLogin sysLogin) {
        SysLoginMapper sysLoginMapper = myBatisUtil.getMapper(SysLoginMapper.class);
        int isInsert = sysLoginMapper.insertSelective(sysLogin);
        return isInsert > 0 ? true : false;
    }
}
