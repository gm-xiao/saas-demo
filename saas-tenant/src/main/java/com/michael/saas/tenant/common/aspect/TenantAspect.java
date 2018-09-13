package com.michael.saas.tenant.common.aspect;

import com.michael.saas.tenant.config.TenantDataSourceProvider;
import com.michael.saas.tenant.util.SpObserver;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class TenantAspect {

    @Pointcut("execution(public * com.michael.saas.tenant.service..*.*(..))")
    public void switchTenant(){}

    @Before("switchTenant()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println(SpObserver.getTenantId());
        TenantDataSourceProvider.getTenantDataSource(SpObserver.getTenantId());
    }

    @AfterReturning(returning = "object", pointcut = "switchTenant()")
    public void doAfterReturning(Object object) throws Throwable {

    }

}
