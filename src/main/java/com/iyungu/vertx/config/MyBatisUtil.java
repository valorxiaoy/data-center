package com.iyungu.vertx.config;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

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

    private static SqlSession sqlSession = null;

    /*
     * 获取数据库访问链接
     */
    public MyBatisUtil() {
        try {
            InputStream stream = Resources.getResourceAsStream(CONFIG_PATH);
            // 可以根据配置的相应环境读取相应的数据库环境
            // SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream, "development");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
            this.sqlSession = factory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T getMapper(Class<T> cls) {
        T mapper = sqlSession.getMapper(cls);
        return mapper;
    }

    public void commit() {
        sqlSession.commit();
    }

    public void closeSession(SqlSession session) {
        session.close();
    }
}
