package com.paipeng.saas.uauth.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paipeng.saas.uauth.master.model.MasterTenant;
import com.paipeng.saas.uauth.master.repository.MasterTenantRepository;

@Service
public class MasterTenantServiceImpl implements MasterTenantService {

    @Autowired
    MasterTenantRepository masterTenantRepo;

    @Override
    public MasterTenant findByTenantId(String tenantId) {
        return masterTenantRepo.findByTenantId(tenantId);
    }

}
