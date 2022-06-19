package com.paipeng.saas.uauth.tenant.config;

import com.paipeng.saas.uauth.tenant.entity.User;
import com.paipeng.saas.uauth.tenant.repository.UserRepository;
import com.paipeng.saas.uauth.tenant.service.UserService;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.paipeng.saas.uauth.tenant.repository",
        "com.paipeng.saas.uauth.tenant.model"})
@EnableJpaRepositories(basePackages = {
        "com.paipeng.saas.uauth.tenant.repository",
        "com.paipeng.saas.uauth.tenant.service"},
        entityManagerFactoryRef = "tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager")
public class TenantDatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(TenantDatabaseConfig.class);

    @Autowired
    private HikariConfigProperties hikariConfigProperties;

    @Bean(name = "tenantJpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "tenantTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("tenantEntityManagerFactory") EntityManagerFactory tenantEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(tenantEntityManager);
        return transactionManager;
    }

    @Bean(name = "datasourceBasedMultitenantConnectionProvider")
    @ConditionalOnBean(name = "masterEntityManagerFactory")
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        // Autowires the multi connection provider
        return new DataSourceBasedMultiTenantConnectionProviderImpl();
    }

    @Bean(name = "currentTenantIdentifierResolver")
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new CurrentTenantIdentifierResolverImpl();
    }

    @Bean(name = "tenantEntityManagerFactory")
    @ConditionalOnBean(name = "datasourceBasedMultitenantConnectionProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("datasourceBasedMultitenantConnectionProvider")
                    MultiTenantConnectionProvider connectionProvider,
            @Qualifier("currentTenantIdentifierResolver")
                    CurrentTenantIdentifierResolver tenantResolver) {

        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        //All tenant related entities, repositories and service classes must be scanned
        emfBean.setPackagesToScan(
                new String[]{User.class.getPackage().getName(),
                        UserRepository.class.getPackage().getName(),
                        UserService.class.getPackage().getName()});
        emfBean.setJpaVendorAdapter(jpaVendorAdapter());
        emfBean.setPersistenceUnitName("tenantdb-persistence-unit");
        Map<String, Object> properties = new HashMap<>();
        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
        // ImprovedNamingStrategy is deprecated and unsupported in Hibernate 5
        // properties.put("hibernate.ejb.naming_strategy",
        // "org.hibernate.cfg.ImprovedNamingStrategy");
        properties.put(Environment.DIALECT, hikariConfigProperties.getDialect());
        properties.put(Environment.SHOW_SQL, hikariConfigProperties.isShowSql());
        properties.put(Environment.FORMAT_SQL, hikariConfigProperties.isFormatSql());
        properties.put(Environment.HBM2DDL_AUTO, hikariConfigProperties.getDdlAuto());
        properties.put(Environment.ALLOW_UPDATE_OUTSIDE_TRANSACTION, hikariConfigProperties.isAllowUpdateOutsideTransaction());

        emfBean.setJpaPropertyMap(properties);
        logger.info("tenantEntityManagerFactory set up successfully!");
        return emfBean;
    }
}