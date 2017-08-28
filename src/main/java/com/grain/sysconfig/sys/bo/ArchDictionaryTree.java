package com.grain.sysconfig.sys.bo;

import java.util.Date;

/**
 * 数据字典类型表
 *
 * @author wzy
 * @serial 2014-11-21 10:08:27
 */
public class ArchDictionaryTree {
    private Integer dic_type_id;//数据字典类型id
    private Integer system_id;//系统id
    private String name;//类型名称
    private Integer parent_id;//父字典类型的id
    private Integer leaf_flag;//叶子节点标志 1：是叶子节点 0：不是叶子节点
    private Date create_time;//创建时间
    private Integer create_user_id;//创建人id
    private Integer last_edited_user_id;//最后修改人id
    private Date last_edited_time;//最后修改时间
    private Integer valid_flag;//有效标 1：有效 0：无效
    private Integer del_flag;//删除标志 1：已删除 0：未删除

    public Integer getDic_type_id() {
        return dic_type_id;
    }

    public void setDic_type_id(Integer dic_type_id) {
        this.dic_type_id = dic_type_id;
    }

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

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getLeaf_flag() {
        return leaf_flag;
    }

    public void setLeaf_flag(Integer leaf_flag) {
        this.leaf_flag = leaf_flag;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id) {
        this.create_user_id = create_user_id;
    }

    public Integer getLast_edited_user_id() {
        return last_edited_user_id;
    }

    public void setLast_edited_user_id(Integer last_edited_user_id) {
        this.last_edited_user_id = last_edited_user_id;
    }

    public Date getLast_edited_time() {
        return last_edited_time;
    }

    public void setLast_edited_time(Date last_edited_time) {
        this.last_edited_time = last_edited_time;
    }

    public Integer getValid_flag() {
        return valid_flag;
    }

    public void setValid_flag(Integer valid_flag) {
        this.valid_flag = valid_flag;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

}
