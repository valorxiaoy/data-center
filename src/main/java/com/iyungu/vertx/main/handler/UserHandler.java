package com.iyungu.vertx.main.handler;

import com.iyungu.vertx.base.ResponseUtil;
import com.iyungu.vertx.base.annotation.bean.RequestMethod;
import com.iyungu.vertx.base.annotation.request.RequestMapping;
import com.iyungu.vertx.base.annotation.request.RestController;
import com.iyungu.vertx.main.bean.po.SysUser;
import com.iyungu.vertx.main.service.ISysLoginService;
import com.iyungu.vertx.main.service.ISysUserService;
import com.iyungu.vertx.main.service.impl.SysLoginServiceImpl;
import com.iyungu.vertx.main.service.impl.SysUserServiceImpl;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 15:53$ 2018/5/16$
 * @Modified By:
 */
@RestController(value = "/user")
public class UserHandler {

    private static Logger logger = LogManager.getLogger(UserHandler.class);

    private ISysUserService sysUserService = new SysUserServiceImpl();

    private ISysLoginService sysLoginService = new SysLoginServiceImpl();

    @RequestMapping(value = "/:id", methodType = RequestMethod.GET)
    public void login(RoutingContext routingContext) {
        SysUser sysUser = null;
        try {
            String id = routingContext.request().getParam("id");
            sysUser = sysUserService.selectById(id);
            logger.debug("/user/:id return;");
            ResponseUtil.success(routingContext, sysUser);
        } catch (Exception ex) {
            ResponseUtil.failedService(routingContext, sysUser);
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/registered", methodType = RequestMethod.POST)
    public void registered(RoutingContext routingContext) {
        boolean isRegistered = false;
        try {
            JsonObject bodyAsJson = routingContext.getBodyAsJson();
            SysUser sysUser = bodyAsJson.mapTo(SysUser.class);
            sysUser = sysUserService.registered(sysUser);
            if (sysUser != null) {
                String loginId = sysUser.getLoginId();
                String userId = sysUser.getId();
                isRegistered = sysLoginService.bindUser(loginId, userId);
            }
            ResponseUtil.success(routingContext, isRegistered);
        } catch (Exception ex) {
            ResponseUtil.failedService(routingContext, isRegistered);
            ex.printStackTrace();
        }
    }
}
