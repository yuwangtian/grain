package com.grain.sysconfig.sys.bo;

import com.grain.base.bo.BaseBo;

/**
 * @author Administrator
 *         table ERP_PROVINCE 省份表
 */
public class ProvinceDictionary extends BaseBo {

    private Integer province_id;

    private String name;

    private String code;

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
