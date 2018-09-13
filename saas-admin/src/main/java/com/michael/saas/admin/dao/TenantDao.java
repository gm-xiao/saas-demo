package com.michael.saas.admin.dao;


import com.michael.saas.admin.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantDao extends JpaRepository<Tenant,String> {

    Tenant findById(String id);

    Tenant findByAccountAndToken(String account,String token);

}
