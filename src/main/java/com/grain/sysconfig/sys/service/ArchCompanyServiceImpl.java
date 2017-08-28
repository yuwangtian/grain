package com.grain.sysconfig.sys.service;

import com.grain.sysconfig.sys.bo.ArchCompany;
import com.grain.sysconfig.sys.dao.ArchCompanyDao;
import com.grain.utils.pageutils.LimitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchCompanyServiceImpl {

    @Autowired
    private ArchCompanyDao dao;

    public List<ArchCompany> getList(ArchCompany obj) {
        return dao.getList((ArchCompany) obj);
    }

    public void saveCompany(ArchCompany obj) {
        dao.insert((ArchCompany) obj);
    }

    public List<ArchCompany> pageList(LimitInfo info) {
        info.setRecordCount(dao.getTotalCount(info));
        return dao.pageList(info);
    }

    public void delbyId(ArchCompany company) {
        dao.delete(company);
    }

    public void updateCompany(ArchCompany obj) {
        dao.update((ArchCompany) obj);
    }

    public ArchCompany getCompanyById(Integer companyId) {
        return dao.getCompanyById(companyId);
    }
}
