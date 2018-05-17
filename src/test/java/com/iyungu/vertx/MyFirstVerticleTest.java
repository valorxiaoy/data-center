/*
package com.iyungu.vertx;

import com.iyungu.vertx.verticle.UserHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

*/
/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: 16:34$ 2018/5/10$
 * @Modified By:
 *//*

@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {

    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(UserHandler.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(8080, "192.168.80.16", "/", response -> {
            response.handler(body -> {
                context.assertTrue(body.toString().contains("Hello"));
                async.complete();
            });
        });
    }
}*/
