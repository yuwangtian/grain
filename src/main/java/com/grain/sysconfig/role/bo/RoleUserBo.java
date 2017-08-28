package com.grain.sysconfig.role.bo;

import com.grain.base.bo.BaseBo;

public class RoleUserBo extends BaseBo {

    private Integer id;
    private Integer user_id;
    private Integer role_id;
    private Integer role_function;//自动处理角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getRole_function() {
        return role_function;
    }

    public void setRole_function(Integer role_function) {
        this.role_function = role_function;
    }
}
