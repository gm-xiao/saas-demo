package com.michael.saas.tenant.web;

import com.michael.saas.tenant.domain.Good;
import com.michael.saas.tenant.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping(value = "/jsonList")
    public List<Good> jsonList(){
        return goodService.selectAll();
    }

    @PostMapping(value = "/save")
    public Good save(Good good){
        return goodService.save(good);
    }

}
