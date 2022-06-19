package com.paipeng.saas.uauth.tenant.repository;

import com.paipeng.saas.uauth.tenant.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}