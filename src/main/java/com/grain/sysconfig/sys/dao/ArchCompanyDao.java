package com.grain.sysconfig.sys.dao;

import com.grain.base.dao.BaseDao;
import com.grain.sysconfig.sys.bo.ArchCompany;
import com.grain.utils.pageutils.LimitInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wzy
 * @since 2014-11-22 09:47:21
 */
@Repository
public interface ArchCompanyDao extends BaseDao {

    List<ArchCompany> getList(ArchCompany obj);

    List<ArchCompany> pageList(LimitInfo info);

    Integer getTotalCount(LimitInfo info);

    ArchCompany getCompanyById(Integer companyId);

}
