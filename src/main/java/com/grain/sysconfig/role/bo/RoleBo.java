package com.grain.sysconfig.role.bo;

import com.grain.base.bo.BaseBo;

import java.io.Serializable;


public class RoleBo extends BaseBo implements Serializable {

    private Integer role_id;

    private String role_name;  //角色姓名

    private String role_type_name; //角色分类名称

    private String role_en_name; //角色英文名称

    private String globe_flag; //全局标志 1：全局角色 0：部门角色

    private Integer role_level; //角色等级 1：普通员工 2：组长 3：经理 4：总监

    private Integer system_id;  //系统主键

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_type_name() {
        return role_type_name;
    }

    public void setRole_type_name(String role_type_name) {
        this.role_type_name = role_type_name;
    }

    public String getGlobe_flag() {
        return globe_flag;
    }

    public void setGlobe_flag(String globe_flag) {
        this.globe_flag = globe_flag;
    }

    public Integer getRole_level() {
        return role_level;
    }

    public void setRole_level(Integer role_level) {
        this.role_level = role_level;
    }

    public Integer getSystem_id() {
        return system_id;
    }

    public void setSystem_id(Integer system_id) {
        this.system_id = system_id;
    }


    public String getRole_en_name() {
        return role_en_name;
    }

    public void setRole_en_name(String role_en_name) {
        this.role_en_name = role_en_name;
    }
}

