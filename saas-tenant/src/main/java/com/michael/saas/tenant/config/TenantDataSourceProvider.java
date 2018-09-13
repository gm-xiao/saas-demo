package com.michael.saas.tenant.config;

import com.michael.saas.tenant.domain.Tenant;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类负责根据租户ID来提供对应的数据源
 * @author lanyuanxiaoyao
 * @version 1.0
 */
@Component
public class TenantDataSourceProvider {

    // 使用一个map来存储我们租户和对应的数据源，租户和数据源的信息就是从我们的tenant_info表中读出来
    private static Map<String, DataSource> dataSourceMap = new HashMap<>();

    /**
     * 静态建立一个数据源，也就是我们的默认数据源，假如我们的访问信息里面没有指定tenantId，就使用默认数据源。
     * 在我这里默认数据源是cloud_config，实际上你可以指向你们的公共信息的库，或者拦截这个操作返回错误。
     */
    static {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://127.0.0.1:3306/saas_tenant?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceMap.put("Default", dataSourceBuilder.build());
    }

    // 根据传进来的tenantId决定返回的数据源
    public static DataSource getTenantDataSource(String tenantId) {
        if (dataSourceMap.containsKey(tenantId)) {
            return dataSourceMap.get(tenantId);
        } else {
            return dataSourceMap.get("Default");
        }
    }

    // 初始化的时候用于添加数据源的方法
    public static void addDataSource(Tenant tenantInfo) {
        if(!dataSourceMap.containsKey(tenantInfo.getId())){
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            String url = tenantInfo.getUrl().concat(tenantInfo.getDatabase()).concat("?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false");
            dataSourceBuilder.url(url);
            dataSourceBuilder.username(tenantInfo.getUsername());
            dataSourceBuilder.password(tenantInfo.getPassword());
            dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
            dataSourceMap.put(tenantInfo.getId(), dataSourceBuilder.build());
        }
    }

}
