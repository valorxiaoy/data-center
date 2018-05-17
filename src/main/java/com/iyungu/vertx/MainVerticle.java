package com.iyungu.vertx;

import com.iyungu.vertx.annotation.AnnotationStart;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 17:19$ 2018/5/11$
 * @Modified By:
 */
public class MainVerticle extends AbstractVerticle {

    private static Logger logger = LogManager.getLogger(MainVerticle.class);

    @Override
    public void start() throws IllegalAccessException, InstantiationException {
        logger.info("开始创建主路由");
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);
        logger.info("主路由创建成功");

        logger.info("开始配置日志系统");
        System.setProperty("vertx.logger-delegate-factory-class-name","io.vertx.core.logging.Log4j2LogDelegateFactory");
        logger.info("日志系统配置成功");

        logger.info("开始创建端口监听");
        vertx.createHttpServer().requestHandler(router::accept).listen(8098);
        logger.info("端口监听创建成功");

        logger.info("开始初始化主路由");
        router.route("/*").handler(BodyHandler.create());
        logger.info("主路由初始化成功");

        logger.info("开始初始化子路由");
        AnnotationStart annotationStart = new AnnotationStart(vertx, router);
        logger.info("子路由初始化成功");

        logger.info("成功启动程序");
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        MainVerticle mainVerticle = new MainVerticle();
        mainVerticle.start();
    }

}