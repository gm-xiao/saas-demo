package com.michael.saas.tenant.common.queue;

import java.io.Serializable;

public class QueueTemplate implements Serializable {

    private static final long serialVersionUID = 4826957158259911927L;

    private String method;

    private Object object;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
