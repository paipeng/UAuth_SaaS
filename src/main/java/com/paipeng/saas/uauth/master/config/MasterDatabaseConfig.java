package com.paipeng.saas.uauth.master.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.paipeng.saas.uauth.master.model.MasterTenant;
import com.paipeng.saas.uauth.master.repository.MasterTenantRepository;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.paipeng.saas.uauth.master.model",
        "com.paipeng.saas.uauth.master.repository" }, entityManagerFactoryRef = "masterEntityManagerFactory", transactionManagerRef = "masterTransactionManager")
public class MasterDatabaseConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MasterDatabaseConfig.class);

    @Autowired
    private MasterDatabaseConfigProperties masterDbProperties;

    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {

        LOG.info("Setting up masterDataSource with: " + masterDbProperties.toString());

        HikariDataSource ds = new HikariDataSource();

        ds.setUsername(masterDbProperties.getUsername());
        ds.setPassword(masterDbProperties.getPassword());
        ds.setJdbcUrl(masterDbProperties.getUrl());
        ds.setDriverClassName(masterDbProperties.getDriverClassName());
        ds.setPoolName(masterDbProperties.getPoolName());

        // HikariCP settings
        // Maximum number of actual connection in the pool
        ds.setMaximumPoolSize(masterDbProperties.getMaxPoolSize());

        // Minimum number of idle connections in the pool
        ds.setMinimumIdle(masterDbProperties.getMinIdle());

        // Maximum waiting time for a connection from the pool
        ds.setConnectionTimeout(masterDbProperties.getConnectionTimeout());

        // Maximum time that a connection is allowed to sit idle in the pool
        ds.setIdleTimeout(masterDbProperties.getIdleTimeout());
        LOG.info("Setup of masterDataSource succeeded.");
        return ds;
    }

    @Primary
    @Bean(name = "masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        // Set the master data source
        em.setDataSource(masterDataSource());

        // The master tenant entity and repository need to be scanned
        em.setPackagesToScan(
                new String[] { MasterTenant.class.getPackage().getName(),
                        MasterTenantRepository.class.getPackage().getName() });
        // Setting a name for the persistence unit as Spring sets it as
        // 'default' if not defined
        em.setPersistenceUnitName("masterdb-persistence-unit");

        // Setting Hibernate as the JPA provider
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Set the hibernate properties
        em.setJpaProperties(hibernateProperties());
        LOG.info("Setup of masterEntityManagerFactory succeeded.");
        return em;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public JpaTransactionManager masterTransactionManager(
            @Qualifier("masterEntityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * The properties for configuring the JPA provider Hibernate.
     * 
     * @return
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(org.hibernate.cfg.Environment.DIALECT, masterDbProperties.getDialect());
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, masterDbProperties.isShowSql());
        properties.put(org.hibernate.cfg.Environment.FORMAT_SQL, masterDbProperties.isFormatSql());
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, masterDbProperties.getDdlAuto());
        return properties;
    }
}