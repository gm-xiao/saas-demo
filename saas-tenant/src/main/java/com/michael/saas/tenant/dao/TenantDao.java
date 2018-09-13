package com.michael.saas.tenant.dao;


import com.michael.saas.tenant.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantDao extends JpaRepository<Tenant,String> {

    Tenant findById(String id);

    Tenant findByAccountAndToken(String account, String token);

}
