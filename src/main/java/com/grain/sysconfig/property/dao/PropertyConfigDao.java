package com.grain.sysconfig.property.dao;

import com.grain.base.dao.BaseDao;
import com.grain.sysconfig.property.bo.PropertyConfigBo;

import java.util.List;

/**
 * Created by Administrator on 2015/5/11.
 */
public interface PropertyConfigDao extends BaseDao {
    public PropertyConfigBo getPropertyByKey(String propertyKey);

    public List<PropertyConfigBo> getPropertyByGroup(String propertyGroup);

}
