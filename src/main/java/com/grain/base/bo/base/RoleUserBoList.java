package com.grain.base.bo.base;

import com.grain.sysconfig.role.bo.RoleUserBo;

import java.util.List;

/**
 * Created by yuchen
 * on 2016/9/6 0006.
 */
public class RoleUserBoList {
    /**
     * 用户角色
     */
    private List<RoleUserBo> roleUserBoList;

    public List<RoleUserBo> getRoleUserBoList() {
        return roleUserBoList;
    }

    public void setRoleUserBoList(List<RoleUserBo> roleUserBoList) {
        this.roleUserBoList = roleUserBoList;
    }
}
