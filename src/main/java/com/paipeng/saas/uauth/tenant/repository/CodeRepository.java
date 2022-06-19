package com.paipeng.saas.uauth.tenant.repository;


import com.paipeng.saas.uauth.tenant.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findBySerialNumber(String serialNumber);

    List<Code> findAllByTaskId(Long taskId);
}

