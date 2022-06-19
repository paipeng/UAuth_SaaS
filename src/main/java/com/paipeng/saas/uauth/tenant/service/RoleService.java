package com.paipeng.saas.uauth.tenant.service;

import com.paipeng.saas.uauth.tenant.entity.Role;

public interface RoleService {

    Role findByRole(String roleName);
}
