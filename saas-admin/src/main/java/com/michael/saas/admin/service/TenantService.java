package com.michael.saas.admin.service;


import com.michael.saas.admin.dao.TenantDao;
import com.michael.saas.admin.domain.Tenant;
import com.michael.saas.admin.util.GUIDHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantDao tenantDao;

    public Tenant findById(String id){
        return tenantDao.findById(id);
    };

    public List<Tenant> selectAll(){
        return tenantDao.findAll();
    };

    public Tenant findByAccountAndToken(String accoun, String token){
        return tenantDao.findByAccountAndToken(accoun, token);
    };

    public Tenant save(Tenant tenant) throws Exception{
        if (StringUtils.isBlank(tenant.getId())){
            tenant.setId(GUIDHelper.genRandomGUID());
        }
        return tenantDao.save(tenant);
    }

}
