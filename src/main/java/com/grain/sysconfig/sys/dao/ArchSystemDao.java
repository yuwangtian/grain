package com.grain.sysconfig.sys.dao;

import com.grain.sysconfig.sys.bo.ArchSystem;
import com.grain.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wzy
 * @since 2014-11-22 09:49:59
 */
@Repository
public interface ArchSystemDao extends BaseDao {

    List<ArchSystem> getList(ArchSystem obj);

}
