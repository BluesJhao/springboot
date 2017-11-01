//package com.example.springboot;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * Desc ：Application Configuration
// * Created by JHAO on 2017/10/31.
// */
//@Configuration
//public class AppConfiguration {
//
//    @Bean(name = "dataSource")
//    @Qualifier(value = "dataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "loan.datasource")
//    public DataSource dataSource(){
//        return new DruidDataSource();
//    }
//
//    @Bean(name = "dataSourceCluster")
//    @Qualifier(value = "dataSourceCluster")
//    @ConfigurationProperties(prefix = "loan.datasource.cluster")
//    public DataSource dataSourceCluster(){
//        return new DruidDataSource();
//    }
//
//    // 其中dataSource框架会自动为我们注入
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager txManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
