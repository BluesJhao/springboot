package com.example.springboot.config.jpa;

/**
 * Desc ：JpaMasterDataSource
 * Created by JHAO on 2017/11/1.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef="master_entityManagerFactory",
        transactionManagerRef="master_transactionManager",
        basePackages= {"com.example.springboot.repository.master"})
public class JpaMasterDataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JpaMasterDataSourceConfiguration.class);

    @Resource(name = "primaryDataSource")
    private DataSource primaryDataSource;

    @Primary
    @Bean(name = "master_entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        logger.info("Initializing Master EntityManager...");
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "master_entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        logger.info("Initializing Master LocalContainerEntityManagerFactoryBean...");
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("com.example.springboot.model.master") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "master_transactionManager")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        logger.info("Initializing Master PlatformTransactionManager...");
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
