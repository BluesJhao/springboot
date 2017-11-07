package com.example.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.springboot.mapper.BasicMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Desc ：Second dataSource
 * Created by JHAO on 2017/10/31.
 */

@Configuration
@MapperScan(basePackages = {"com.example.springboot.mapper.second"},sqlSessionFactoryRef = "second_sqlSessionFactory",markerInterface = BasicMapper.class)
public class SecondDataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SecondDataSourceConfiguration.class);

    /**
     * Setup DataSource
     *
     */
    @Bean(name = "secondDatasource")
    @ConfigurationProperties(prefix = "second.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    /**
     * Config SqlSessionFactory
     *
     */
    @Bean(name = "second_sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(){
        logger.info("Initializing Second SqlSessionFactory...");
        SqlSessionFactory sqlSessionFactory = null;
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        try {
            //设置数据源 datasource
            DataSource dataSource = dataSource();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return sqlSessionFactory;
    }

    /**
     * Config TransactionManager
     * 如果使用starter-data-jpa或starter-jdbc无需配置
     * springboot默认使用JpaTransactionManager或DataSourceTransactionManager自动配置
     *
     */
    @Bean(name = "second_transactionManager")
    public PlatformTransactionManager transactionManager() throws SQLException {
        logger.info("Initializing Second DataSourceTransactionManager with datasource");
        return new DataSourceTransactionManager(dataSource());
    }

}
