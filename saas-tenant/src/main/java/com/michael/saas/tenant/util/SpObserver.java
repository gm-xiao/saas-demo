package com.michael.saas.tenant.util;
public class SpObserver {

    private static ThreadLocal<String> tenantIdLocal = new ThreadLocal<String>();

    public static void putTenantId(String tenantId) {
        tenantIdLocal.set(tenantId);
    }

    public static String getTenantId() {
        return (String) tenantIdLocal.get();
    }

}