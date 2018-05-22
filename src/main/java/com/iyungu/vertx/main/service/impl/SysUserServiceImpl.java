package com.iyungu.vertx.main.service.impl;

import com.iyungu.vertx.base.mybatis.config.MyBatisUtil;
import com.iyungu.vertx.main.bean.po.SysUser;
import com.iyungu.vertx.main.dao.SysUserMapper;
import com.iyungu.vertx.main.service.ISysUserService;

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
        // myBatisUtil.closeSession();
        return sysUser;
    }

    @Override
    public SysUser registered(SysUser sysUser) {
        SysUserMapper sysUserMapper = myBatisUtil.getMapper(SysUserMapper.class);
        boolean isInsert = sysUserMapper.insertSelective(sysUser) > 0;
        myBatisUtil.commit(isInsert);
        // myBatisUtil.closeSession();
        return isInsert ? sysUser : null;
    }
}