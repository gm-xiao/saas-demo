package com.michael.saas.tenant.service;

import com.michael.saas.tenant.dao.GoodDao;
import com.michael.saas.tenant.domain.Good;
import com.michael.saas.tenant.util.GUIDHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {

    @Autowired
    private GoodDao goodDao;

    public List<Good> selectAll(){
        return goodDao.findAll();
    }

    public Good save(Good good){
        if (StringUtils.isBlank(good.getId())){
            good.setId(GUIDHelper.genRandomGUID());
        }
        return goodDao.save(good);
    }

}
