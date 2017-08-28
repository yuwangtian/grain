package com.grain.sysconfig.sys.dao;

import com.grain.base.dao.BaseDao;
import com.grain.sysconfig.sys.bo.ArchDictionaryTree;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wzy
 * @since 2014-11-22 09:52:31
 */
@Repository
public interface ArchDictionaryTreeDao extends BaseDao {

    List<ArchDictionaryTree> getList(ArchDictionaryTree obj);

}
