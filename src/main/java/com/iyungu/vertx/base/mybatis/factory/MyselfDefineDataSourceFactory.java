package com.iyungu.vertx.base.mybatis.factory;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: yueyang@iyungu.com
 * @Description: 自定义数据源工厂
 * @Date: Created in 9:29 2018/4/18
 * @Modified By:
 */
public class MyselfDefineDataSourceFactory extends UnpooledDataSourceFactory {

    public MyselfDefineDataSourceFactory() throws IOException {

        String CONFIG_PATH = "config/dbConfig.properties";
        InputStream stream = Resources.getResourceAsStream(CONFIG_PATH);
        Properties properties = new Properties();
        properties.load(stream);
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();
        this.dataSource = dataSource;
    }
}