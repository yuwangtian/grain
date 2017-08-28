package com.grain.sysconfig.sys.service;

import com.grain.sysconfig.sys.dao.ArchProductDao;
import com.grain.sysconfig.sys.bo.ArchProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchProductServiceImpl {

    @Autowired
    private ArchProductDao dao;

    public List<ArchProduct> getList(ArchProduct obj) {
        // TODO Auto-generated method stub
        return dao.getList(obj);
    }
}
