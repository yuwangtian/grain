package com.grain.sysconfig.sys.bo;

import com.grain.base.bo.BaseBo;

/**
 * @author Administrator
 *         table ERP_PROVINCE 市表
 */
public class CityDictionary extends BaseBo {

    private Integer city_id;

    private Integer province_id;

    private String name;

    private String code;

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
