package com.paipeng.saas.uauth.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paipeng.saas.uauth.master.model.MasterTenant;

import java.util.List;

@Repository
public interface MasterTenantRepository
        extends JpaRepository<MasterTenant, Long> {

    /**
     * Using a custom named query
     * @param tenantId
     * @return
     */
    @Query("select p from MasterTenant p where p.tenantId = :tenantId")
    MasterTenant findByTenantId(@Param("tenantId") String tenantId);

    List<MasterTenant> findAllByOrderByIdAsc();
    List<MasterTenant> findAllByOrderByIdDesc();
}
