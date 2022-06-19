
package com.paipeng.saas.uauth.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paipeng.saas.uauth.tenant.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Query to find a Role entiry based on the {@link Role} name
     * 
     * @param role
     * @return
     */
    Role findByRole(String role);
}
