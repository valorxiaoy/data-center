package com.iyungu.vertx.server.handlers;

import com.iyungu.vertx.annotation.RequestMapping;
import com.iyungu.vertx.annotation.RequestMethod;
import com.iyungu.vertx.annotation.RestController;
import com.iyungu.vertx.util.BusinessException;
import com.iyungu.vertx.util.ResponseUtil;
import com.iyungu.vertx.server.bean.po.SysLogin;
import com.iyungu.vertx.server.bean.po.SysUser;
import com.iyungu.vertx.server.service.ISysLoginService;
import com.iyungu.vertx.server.service.ISysUserService;
import com.iyungu.vertx.server.service.impl.SysLoginServiceImpl;
import com.iyungu.vertx.server.service.impl.SysUserServiceImpl;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 9:42$ 2018/5/15$
 * @Modified By:
 */
@RestController(value = "/user-login")
public class LoginHandler {

    private ISysLoginService sysLoginService = new SysLoginServiceImpl();

    @RequestMapping(value = "/:loginName", methodType = RequestMethod.GET)
    public void login(RoutingContext routingContext) {
        String loginName = routingContext.request().getParam("loginName");
        SysLogin sysLogin = sysLoginService.login(loginName);
        ResponseUtil.success(routingContext, sysLogin);
    }

    @RequestMapping(value = "/registered", methodType = RequestMethod.POST)
    public void registered(RoutingContext routingContext) {
        boolean isRegistered = false;
        try {
            JsonObject bodyAsJson = routingContext.getBodyAsJson();
            SysLogin sysLogin = bodyAsJson.mapTo(SysLogin.class);
            isRegistered = sysLoginService.registered(sysLogin);
        } catch (Exception ex) {
            throw new BusinessException("");
        } finally {
            ResponseUtil.success(routingContext, isRegistered);
        }
    }
}