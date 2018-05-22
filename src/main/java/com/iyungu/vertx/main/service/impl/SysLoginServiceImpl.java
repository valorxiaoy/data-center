package com.iyungu.vertx.main.service.impl;

import com.iyungu.vertx.base.mybatis.config.MyBatisUtil;
import com.iyungu.vertx.main.bean.po.SysLogin;
import com.iyungu.vertx.main.dao.SysLoginMapper;
import com.iyungu.vertx.main.service.ISysLoginService;

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
        // myBatisUtil.closeSession();
        return sysLogin;
    }

    public boolean registered(SysLogin sysLogin) {
        SysLoginMapper sysLoginMapper = myBatisUtil.getMapper(SysLoginMapper.class);
        boolean isInsert = sysLoginMapper.insertSelective(sysLogin) > 0;
        myBatisUtil.commit(isInsert);
        // myBatisUtil.closeSession();
        return isInsert;
    }

    public boolean bindUser(String loginId, String userId) {
        SysLoginMapper sysLoginMapper = myBatisUtil.getMapper(SysLoginMapper.class);
        SysLogin sysLogin = new SysLogin();
        sysLogin.setId(loginId);
        sysLogin.setUserId(userId);
        boolean isUpdate = sysLoginMapper.updateByPrimaryKeySelective(sysLogin) > 0;
        myBatisUtil.commit(isUpdate);
        // myBatisUtil.closeSession();
        return isUpdate;

    }
}
