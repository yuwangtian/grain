package com.grain.sysconfig.role.bo;

import com.grain.base.bo.BaseBo;

public class SystemBo extends BaseBo {

    private Integer system_id;

    private String name;

    public Integer getSystem_id() {
        return system_id;
    }

    public void setSystem_id(Integer system_id) {
        this.system_id = system_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
