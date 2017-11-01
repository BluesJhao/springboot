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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Desc ：Primary DataSource
 * Created by JHAO on 2017/10/31.
 */
@Configuration
@MapperScan(basePackages = {"com.example.springboot.mapper.primary"},sqlSessionFactoryRef = "primary_sqlSessionFactory",markerInterface = BasicMapper.class)
public class PrimaryDataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(PrimaryDataSourceConfiguration.class);

    /**
     * Setup DataSource
     *
     */
    @Bean(name = "primaryDataSource")
    @Primary//在注入时：多个同类型bean, @Primary则优先
    @ConfigurationProperties(prefix = "primary.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    /**
     * Config SqlSessionFactory
     *
     */
    @Bean(name = "primary_sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(){
        logger.info("Initializing Primary SqlSessionFactory...");
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
    @Bean(name = "primary_transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() throws SQLException {
        logger.info("Initializing Primary DataSourceTransactionManager with datasource '{}'", dataSource());
        return new DataSourceTransactionManager(dataSource());
    }

}
