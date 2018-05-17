package com.iyungu.vertx.annotation;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AnnotationStart {

    private Vertx vertx;

    private Router mainRouter;

    private static Map<String, List<ExecutorBean>> map;

    public AnnotationStart(Vertx vertx, Router mainRouter) throws InstantiationException, IllegalAccessException {
        map = AnnoManageUtil.getRequestMappingMethod("com.iyungu.vertx.server.handlers");
        this.vertx = vertx;
        this.mainRouter = mainRouter;
        createRouter();
    }

    private void createRouter() {
        for(Map.Entry<String, List<ExecutorBean>> entry : map.entrySet()){
            // 子路由命名空间
            String namespace = entry.getKey();
            // 子路由方法
            List<ExecutorBean> list = entry.getValue();
            // 加载子路由
            load(namespace, list);
        }
    }

    private void load(String namespace, List<ExecutorBean> list) {
        // 初始化子路由
        Router router = Router.router(vertx);
        for (ExecutorBean bean : list) {
            String value = bean.getValue();
            Method method = bean.getMethod();
            String methodType = bean.getMethodType();
            Class cls = bean.getCls();
            Object classInstance = bean.getClassInstance();
            // 创建处理器
            Handler<RoutingContext> handler = createHandler(classInstance, method);
            // 配置子路由
            deployRouter(methodType, value, handler, router);
        }
        // 注册子路由
        mainRouter.mountSubRouter(namespace, router);
    }

    private Handler<RoutingContext> createHandler(Object classInstance, Method method) {
        Handler<RoutingContext> var = new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                try {
                    method.invoke(classInstance, routingContext);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        return var;
    }

    private Router deployRouter(String methodType, String value, Handler<RoutingContext> handler, Router router) {
        switch (methodType) {
            case RequestMethod.GET :
                router.get(value).handler(handler);
                break;
            case RequestMethod.POST :
                router.post(value).handler(handler);
                break;
            case RequestMethod.PUT :
                router.put(value).handler(handler);
                break;
            case RequestMethod.DELETE :
                router.delete(value).handler(handler);
                break;
            default: break;
        }
        return router;
    }

}
