package com.michael.saas.admin.common.runner;

import com.michael.saas.admin.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskRunner implements ApplicationRunner {

    @Autowired
    private TenantService tenantService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }
}
