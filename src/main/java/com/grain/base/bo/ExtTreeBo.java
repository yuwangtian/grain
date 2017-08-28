package com.grain.base.bo;

import java.util.List;

public class ExtTreeBo {

    private Integer id;

    private String text;

    private Integer parentId; // 父节点id

    private Integer main_company_id;//所属公司

    private boolean expanded; // 是否展开

    private String icon; // 图标

    private boolean leaf; // 是否子节点

    private boolean root;// 是否根节点

    private List<ExtTreeBo> children;

    private Integer type; //节点类型：1总部 2分公司3合作公司4合资公司20大部门 21小部门22小组

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public List<ExtTreeBo> getChildren() {
        return children;
    }

    public void setChildren(List<ExtTreeBo> children) {
        this.children = children;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMain_company_id() {
        return main_company_id;
    }

    public void setMain_company_id(Integer main_company_id) {
        this.main_company_id = main_company_id;
    }


}
