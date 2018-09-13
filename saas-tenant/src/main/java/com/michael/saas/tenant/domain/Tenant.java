package com.michael.saas.tenant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tenant")
public class Tenant implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "account",length = 30)
    private String account;

    @Column(name = "token",length = 32)
    private String token;

    @Column(name = "url",length = 125)
    private String url;

    @Column(name = "data_base",length = 30)
    private String database;

    @Column(name = "username",length = 30)
    private String username;

    @Column(name = "password",length = 32)
    private String password;

    @Column(name = "domain_name",length = 64)
    private String domainName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
