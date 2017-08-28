package com.grain.base.bo;

/**
 * 树形BO
 * 2014-12-10
 *
 * @author yuchen
 */
public class TreeBo extends BaseBo {
    /**
     * 节点ID
     */
    private String id;

    /**
     * 节点名称
     */
    private String name;

    private String en_name;

    /**
     * 父节点ID
     */
    private String pId;

    private boolean open;

    private boolean nocheck;

    private boolean checked;

    /**
     * 是否为菜单
     */
    private boolean isParent;
    /**
     *  子节点
     */
    //private List<TreeBo> children;
    /**
     * 类型：
     * 1：总部
     * 2：分公司
     * 3：合作公司
     * 4：合资公司
     * 20:大部门
     * 21:小部门
     * 22:小组
     * 23:事业部
     */
    private String type;

    /**
     * 职能类型
     */
    private String funType;

    /**
     * 自定义图标
     */
    private String icon;


    public String getpId() {
        return pId;
    }


    public void setpId(String pId) {
        this.pId = pId;
    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }


    public String getFunType() {
        return funType;
    }


    public void setFunType(String funType) {
        this.funType = funType;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public boolean getIsParent() {
        return isParent;
    }


    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    public String getEn_name() {
        return en_name;
    }


    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }


}
