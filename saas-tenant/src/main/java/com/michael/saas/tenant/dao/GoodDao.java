package com.michael.saas.tenant.dao;


import com.michael.saas.tenant.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodDao extends JpaRepository<Good,String> {


}
