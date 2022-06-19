package com.paipeng.saas.uauth.tenant.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.paipeng.saas.uauth.util.TenantContextHolder;

public class CurrentTenantIdentifierResolverImpl
        implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT_ID = "tenant_1";

    private final static Logger logger = LogManager.getLogger(CurrentTenantIdentifierResolverImpl.class.getSimpleName());
    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant = TenantContextHolder.getTenant();
        logger.info("resolveCurrentTenantIdentifier: " + tenant);
        return StringUtils.isNotBlank(tenant) ? tenant : DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
