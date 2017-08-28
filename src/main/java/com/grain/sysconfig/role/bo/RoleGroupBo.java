package com.grain.sysconfig.role.bo;

import com.grain.base.bo.BaseBo;

import java.io.Serializable;


public class RoleGroupBo extends BaseBo implements Serializable {

    private Integer id;
    private Integer group_id;
    private String group_name;
    private Integer role_id;
    private Integer group_type;
    private String main_group_id;
    private String main_group_name;
    private Integer nodeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getGroup_type() {
        return group_type;
    }

    public void setGroup_type(Integer group_type) {
        this.group_type = group_type;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getMain_group_name() {
        return main_group_name;
    }

    public void setMain_group_name(String main_group_name) {
        this.main_group_name = main_group_name;
    }

    public String getMain_group_id() {
        return main_group_id;
    }

    public void setMain_group_id(String main_group_id) {
        this.main_group_id = main_group_id;
    }
}