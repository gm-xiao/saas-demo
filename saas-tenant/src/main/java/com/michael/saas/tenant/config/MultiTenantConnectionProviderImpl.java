package com.michael.saas.tenant.config;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.sql.DataSource;

public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    // 在没有提供tenantId的情况下返回默认数据源
    @Override
    protected DataSource selectAnyDataSource() {
        return TenantDataSourceProvider.getTenantDataSource("Default");
    }

    // 提供了tenantId的话就根据ID来返回数据源
    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return TenantDataSourceProvider.getTenantDataSource(tenantIdentifier);
    }
}
