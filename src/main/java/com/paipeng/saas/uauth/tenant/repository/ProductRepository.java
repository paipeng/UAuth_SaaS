package com.paipeng.saas.uauth.tenant.repository;


import com.paipeng.saas.uauth.tenant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
