package com.grain.sysconfig.sys.service;

import com.grain.sysconfig.sys.bo.ArchDictionaryTree;
import com.grain.sysconfig.sys.dao.ArchDictionaryTreeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchDictionaryTreeServiceImpl {

    @Autowired
    private ArchDictionaryTreeDao dao;


    public List<ArchDictionaryTree> getList(ArchDictionaryTree obj) {
        // TODO Auto-generated method stub
        return dao.getList(obj);
    }

}
