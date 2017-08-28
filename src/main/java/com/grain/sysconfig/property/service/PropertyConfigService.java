package com.grain.sysconfig.property.service;

import com.grain.sysconfig.property.bo.PropertyConfigBo;
import com.grain.sysconfig.property.dao.PropertyConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hujinbo on 2015/8/5.
 */
@Service
public class PropertyConfigService {
    @Autowired
    private PropertyConfigDao propertyConfigDao;

    public PropertyConfigBo getPropertyByKey(String propertyKey) {
        return propertyConfigDao.getPropertyByKey(propertyKey);
    }

    public List<PropertyConfigBo> getPropertyByGroup(String propertyGroup) {
        return propertyConfigDao.getPropertyByGroup(propertyGroup);
    }

    /**
     * 如果需要进行修改，可以去base_property_config去配置
     *
     * @param propertyKey
     * @return
     */
    public String getPropertyValueByKey(String propertyKey) {
        String value = null;
        PropertyConfigBo propertyConfigBo = propertyConfigDao.getPropertyByKey(propertyKey);
        if (propertyConfigBo == null || propertyConfigBo.getProperty_value() == null || "".equals(propertyConfigBo.getProperty_value())) {
            return null;
        } else {
            value = propertyConfigBo.getProperty_value();
        }
        return value;
    }

    /**
     * 从一组属性中根据key找value
     * 主要用在根据属性组取属性时，进而再从本地取具体的属性值，
     * 这样可以减少访问数据库次数
     *
     * @param propertyKey
     * @param propertyConfigBoList
     * @return
     */
    public String getPropertyValueByKey(String propertyKey, List<PropertyConfigBo> propertyConfigBoList) {
        String value = null;
        for (PropertyConfigBo configBo : propertyConfigBoList) {
            if (propertyKey.equals(configBo.getProperty_key())) {
                value = configBo.getProperty_value();
            }
        }
        return value;
    }
}
