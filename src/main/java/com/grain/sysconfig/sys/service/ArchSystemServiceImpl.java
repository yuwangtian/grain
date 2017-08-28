package com.grain.sysconfig.sys.service;

import com.grain.sysconfig.sys.bo.ArchSystem;
import com.grain.sysconfig.sys.dao.ArchSystemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchSystemServiceImpl {

    @Autowired
    private ArchSystemDao dao;

    public List<ArchSystem> getList(ArchSystem obj) {
        return dao.getList(obj);
    }
}
