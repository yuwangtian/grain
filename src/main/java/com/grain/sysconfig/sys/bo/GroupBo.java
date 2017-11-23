package com.grain.sysconfig.sys.bo;

import com.grain.base.bo.BaseBo;

/**
 * 部门
 *
 * @author wzy
 * @table base_group
 * @since 2014-11-21 16:55:43
 */
public class GroupBo extends BaseBo {

    public static Integer GROUP_LEVEL_BIG_DEPT = 20;
    public static Integer GROUP_LEVEL_SMALL_DEPT = 21;
    public static Integer GROUP_LEVEL_GROUP = 22;
    public static Integer GROUP_LEVEL_DIVISION = 23;
    public static final String NOTALL = "notAll";
    private String group_id;
    private String name;//名称
    private String code;
    private String password;
    private String group_company_name;//公司部门合称（转单用）
    private Integer main_company_id;//所属公司id
    private String companyName;//所属公司name
    private Integer function_type; // 职能类型   1：销售 2：服务  3：职能
    private Integer group_level; //部门层级：20：大部门 21：小部门 22：小组 23:事业部
    private Integer parent_id;//父id
    private String comIds;//查询用 查询所在公司的所有部门
    private String groupIds;//查询用 查询显示部门
    private String allFlag;//是否查询所有部门,参数为notAll为不查询所有部门参数为空为查询所有部门(包括已删除的部门)
    private String time_zone;//时区


    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAllFlag() {
        return allFlag;
    }

    public void setAllFlag(String allFlag) {
        this.allFlag = allFlag;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public Integer getMain_company_id() {
        return main_company_id;
    }

    public void setMain_company_id(Integer main_company_id) {
        this.main_company_id = main_company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFunction_type() {
        return function_type;
    }

    public void setFunction_type(Integer function_type) {
        this.function_type = function_type;
    }

    public Integer getGroup_level() {
        return group_level;
    }

    public void setGroup_level(Integer group_level) {
        this.group_level = group_level;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getComIds() {
        return comIds;
    }

    public void setComIds(String comIds) {
        this.comIds = comIds;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGroup_company_name() {
        return group_company_name;
    }

    public void setGroup_company_name(String group_company_name) {
        this.group_company_name = group_company_name;
    }
}
