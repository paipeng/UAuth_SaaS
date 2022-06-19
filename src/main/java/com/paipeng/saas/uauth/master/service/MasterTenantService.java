package com.paipeng.saas.uauth.master.service;

import org.springframework.data.repository.query.Param;

import com.paipeng.saas.uauth.master.model.MasterTenant;

public interface MasterTenantService {
    
    MasterTenant findByTenantId(@Param("tenantId") String tenantId);
}
