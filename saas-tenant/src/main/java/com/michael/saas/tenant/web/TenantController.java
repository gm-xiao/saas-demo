package com.michael.saas.tenant.web;

import com.michael.saas.tenant.domain.Tenant;
import com.michael.saas.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping(value = "/login")
    public boolean login(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request ){
        try {
            Tenant tenant = tenantService.findByAccountAndToken(username, password);
            if (null != tenant){
                request.getSession().setAttribute("TENANTID",tenant.getId());
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
