package com.paipeng.saas.uauth.tenant.config;

import com.paipeng.saas.uauth.master.model.MasterTenant;
import com.paipeng.saas.uauth.master.repository.MasterTenantRepository;
import com.paipeng.saas.uauth.tenant.entity.CustomUserDetails;
import com.paipeng.saas.uauth.util.AvailableTenantsInformationHolder;
import com.paipeng.saas.uauth.util.DataSourceUtil;
import com.paipeng.saas.uauth.util.TenantContextHolder;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);

    private static final long serialVersionUID = 1L;

    /**
     * Injected MasterTenantRepository to access the tenant information from the master_tenant table
     */
    @Autowired
    private MasterTenantRepository masterTenantRepo;

    @Autowired
    private HikariConfigProperties hikariConfigProperties;
    /**
     * Map to store the tenant ids as key and the data source as the value
     */

    @Override
    protected DataSource selectAnyDataSource() {
        logger.info("selectAnyDataSource");
        // This method is called more than once. So check if the data source map
        // is empty. If it is then rescan master_tenant table for all tenant
        // entries.
        if (AvailableTenantsInformationHolder.getAvailableTenants().isEmpty()) {
            List<MasterTenant> masterTenants = masterTenantRepo.findAllByOrderByIdDesc();
            logger.info(">>>> selectAnyDataSource() -- Total tenants:" + masterTenants.size());
            for (MasterTenant masterTenant : masterTenants) {
                logger.info(">> " + masterTenant.getTenantId());

                AvailableTenantsInformationHolder.put(masterTenant.getTenantId(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant, hikariConfigProperties));
            }
        }


        return AvailableTenantsInformationHolder.getFirstDataSource();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        // If the requested tenant id is not present check for it in the master
        // database 'master_tenant' table
        logger.info("selectDataSource: " + tenantIdentifier);
        tenantIdentifier = initializeTenantIfLost(tenantIdentifier);

        if (!AvailableTenantsInformationHolder.getAvailableTenants().containsKey(tenantIdentifier)) {
            List<MasterTenant> masterTenants = masterTenantRepo.findAll();
            logger.info(
                    ">>>> selectDataSource() -- tenant:" + tenantIdentifier + " Total tenants:" + masterTenants.size());
            for (MasterTenant masterTenant : masterTenants) {
                if (AvailableTenantsInformationHolder.getAvailableTenants().containsKey(masterTenant.getTenantId())) {
                    continue;
                }
                AvailableTenantsInformationHolder.put(masterTenant.getTenantId(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant, hikariConfigProperties));
            }
        }
        //check again if tenant exist in map after rescan master_db, if not, throw UsernameNotFoundException
        if (!AvailableTenantsInformationHolder.getAvailableTenants().containsKey(tenantIdentifier)) {
            logger.warn("Trying to get tenant:" + tenantIdentifier + " which was not found in master db after rescan");
            throw new UsernameNotFoundException(
                    String.format(
                            "Tenant not found after rescan, "
                                    + " tenant=%s",
                            tenantIdentifier));
        }
        return AvailableTenantsInformationHolder.getAvailableTenants().get(tenantIdentifier);
    }

    /**
     * Initialize tenantId based on the logged in user if the tenant Id got lost in after form submission in a user
     * session.
     *
     * @param tenantIdentifier
     * @return tenantIdentifier
     */
    private String initializeTenantIfLost(String tenantIdentifier) {
        logger.info("initializeTenantIfLost: " + tenantIdentifier);
        if (TenantContextHolder.getTenant() == null) {

            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            CustomUserDetails customUserDetails = null;
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                customUserDetails = principal instanceof CustomUserDetails ? (CustomUserDetails) principal : null;
            }
            if (customUserDetails != null) {
                TenantContextHolder.setTenantId(customUserDetails.getTenant());
            } else {
                TenantContextHolder.setTenantId(tenantIdentifier);
            }
        }

        if (tenantIdentifier != TenantContextHolder.getTenant()) {
            tenantIdentifier = TenantContextHolder.getTenant();
        }
        return tenantIdentifier;
    }
}
