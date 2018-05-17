package com.iyungu.vertx.server.service.impl;

import com.iyungu.vertx.config.MyBatisUtil;
import com.iyungu.vertx.server.bean.po.SysUser;
import com.iyungu.vertx.server.dao.SysUserMapper;
import com.iyungu.vertx.server.service.ISysUserService;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 14:49$ 2018/5/16$
 * @Modified By:
 */
public class SysUserServiceImpl implements ISysUserService {

    private MyBatisUtil myBatisUtil = new MyBatisUtil();

    @Override
    public SysUser selectById(String id) {
        SysUserMapper sysUserMapper = myBatisUtil.getMapper(SysUserMapper.class);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        return sysUser;
    }

    @Override
    public boolean registered(SysUser sysUser) {
        SysUserMapper sysUserMapper = myBatisUtil.getMapper(SysUserMapper.class);
        int isInsert = sysUserMapper.insertSelective(sysUser);
        return isInsert > 0 ? true : false;
    }
}
