package com.iyungu.vertx.main.handler;

import com.iyungu.vertx.base.BusinessException;
import com.iyungu.vertx.base.ResponseUtil;
import com.iyungu.vertx.base.annotation.bean.RequestMethod;
import com.iyungu.vertx.base.annotation.request.RequestMapping;
import com.iyungu.vertx.base.annotation.request.RestController;
import com.iyungu.vertx.main.bean.po.SysLogin;
import com.iyungu.vertx.main.service.ISysLoginService;
import com.iyungu.vertx.main.service.impl.SysLoginServiceImpl;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 9:42$ 2018/5/15$
 * @Modified By:
 */
@RestController(value = "/user-login")
public class LoginHandler {

    private static Logger logger = LogManager.getLogger(LoginHandler.class);

    private ISysLoginService sysLoginService = new SysLoginServiceImpl();

    @RequestMapping(value = "/:loginName", methodType = RequestMethod.GET)
    public void login(RoutingContext routingContext) {
        SysLogin sysLogin = null;
        try {
            String loginName = routingContext.request().getParam("loginName");
            sysLogin = sysLoginService.login(loginName);
            logger.debug("/user-login/:loginName return;");
            ResponseUtil.success(routingContext, sysLogin);
        } catch (Exception ex) {
            throw new BusinessException("方法异常. ex : " + ex.getMessage(), ex);
        } finally {
            logger.debug("/user-login/registered return;");
            ResponseUtil.failedService(routingContext, sysLogin);
        }
    }

    @RequestMapping(value = "/registered", methodType = RequestMethod.POST)
    public void registered(RoutingContext routingContext) {
        boolean isRegistered = false;
        try {
            JsonObject bodyAsJson = routingContext.getBodyAsJson();
            SysLogin sysLogin = bodyAsJson.mapTo(SysLogin.class);
            isRegistered = sysLoginService.registered(sysLogin);
        } catch (Exception ex) {
            throw new BusinessException("方法异常. ex : " + ex.getMessage(), ex);
        } finally {
            logger.debug("/user-login/registered return;");
            ResponseUtil.failedService(routingContext, isRegistered);
        }
    }
}