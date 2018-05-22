package com.iyungu.vertx.base.mybatis.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @Author: yueyang@iyungu.com
 * @Description:
 * @Date: Created in 16:44 2018/4/17
 * @Modified By:
 */
public class MyBatisUtil {
    /*
     * 定义配置文件的位置
     */
    private static final String CONFIG_PATH = "config/mybatis-config.xml";

    private SqlSession sqlSession = null;

    private void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private void init() {
        try {
            InputStream stream = Resources.getResourceAsStream(CONFIG_PATH);
            // 可以根据配置的相应环境读取相应的数据库环境
            // SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream, "development");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
            setSqlSession(factory.openSession());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T getMapper(Class<T> cls) {
        init();
        T mapper = sqlSession.getMapper(cls);
        return mapper;
    }

    public void commit(boolean isCommit) {
        sqlSession.commit(isCommit);
    }

    public void rollback() {
        sqlSession.rollback();
    }

    public void closeSession() {
        sqlSession.close();
    }
}
