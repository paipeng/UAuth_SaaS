package com.paipeng.saas.uauth.tenant.repository;

import com.paipeng.saas.uauth.tenant.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
