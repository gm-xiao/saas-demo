package com.michael.saas.admin.web;

import com.michael.saas.admin.common.queue.QueueService;
import com.michael.saas.admin.domain.Tenant;
import com.michael.saas.admin.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private QueueService queueService;

    @Autowired
    private TenantService tenantService;

    @PostMapping(value = "/register")
    public boolean register(Tenant tenant){
        try {
            tenant.setUrl("jdbc:mysql://127.0.0.1:3306/");
            tenant.setDatabase("saas_tenant_" + tenant.getAccount());
            tenant.setUsername("root");
            tenant.setPassword("root");
            tenant = tenantService.save(tenant);
            queueService.send("register",tenant);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
