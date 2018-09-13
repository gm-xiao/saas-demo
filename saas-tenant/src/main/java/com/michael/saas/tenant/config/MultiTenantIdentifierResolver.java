package com.michael.saas.tenant.config;


import com.michael.saas.tenant.util.SpObserver;
import org.apache.commons.lang.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;


public class MultiTenantIdentifierResolver implements CurrentTenantIdentifierResolver{

    // 获取tenantId的逻辑在这个方法里面写
    @Override
    public String resolveCurrentTenantIdentifier() {
        if (StringUtils.isNotBlank(SpObserver.getTenantId())){
            return SpObserver.getTenantId();
        }
        return "Default";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
