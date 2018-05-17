package com.iyungu.vertx.server.handlers;

import com.iyungu.vertx.annotation.RequestMapping;
import com.iyungu.vertx.annotation.RequestMethod;
import com.iyungu.vertx.annotation.RestController;
import com.iyungu.vertx.server.bean.po.SysUser;
import com.iyungu.vertx.server.service.ISysUserService;
import com.iyungu.vertx.server.service.impl.SysUserServiceImpl;
import com.iyungu.vertx.util.ResponseUtil;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 15:53$ 2018/5/16$
 * @Modified By:
 */
@RestController(value = "/user")
public class UserHandler {

    private ISysUserService sysUserService = new SysUserServiceImpl();

    @RequestMapping(value = "/registered", methodType = RequestMethod.POST)
    public void registered(RoutingContext routingContext) {
        boolean isRegistered = false;
        try {
            JsonObject bodyAsJson = routingContext.getBodyAsJson();
            SysUser sysUser = bodyAsJson.mapTo(SysUser.class);
            isRegistered = sysUserService.registered(sysUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ResponseUtil.success(routingContext, isRegistered);
        }
    }
}
