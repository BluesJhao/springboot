package com.example.springboot.config.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Desc ：
 * Created by JHAO on 2017/11/1.
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef="slave_entityManagerFactory",
        transactionManagerRef="slave_transactionManager",
        basePackages= {"com.example.springboot.repository.slave"})
public class JpaSlaveDataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JpaSlaveDataSourceConfiguration.class);

    @Resource(name = "secondDatasource")
    private DataSource primaryDataSource;

    @Bean(name = "slave_entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        logger.info("Initializing Slave EntityManager...");
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Bean(name = "slave_entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        logger.info("Initializing Slave LocalContainerEntityManagerFactoryBean...");
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("com.example.springboot.model.slave") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "slave_transactionManager")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        logger.info("Initializing Slave PlatformTransactionManager...");
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}
