package com.grain.base.bo.base;

import java.util.List;

/**
 * 菜单对象
 *
 * @author yuchen
 * @date 2014-12-11
 */
public class MenuBo {
    /**
     * 菜单Id
     */
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单图片
     */
    private String menuImg;
    /**
     * 菜单路径
     */
    private String menuUrl;
    /**
     * 菜单类型（1：一级菜单；2：二级菜单；3:三级菜单；4：按钮；5：跳转链接）
     */
    private String menuType;
    /**
     * 父菜单Id
     */
    private Integer parentId;

    private List<MenuBo> menuBoList;
    /**
     * 排序
     */
    private Integer sortNum;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public List<MenuBo> getMenuBoList() {
        return menuBoList;
    }

    public void setMenuBoList(List<MenuBo> menuBoList) {
        this.menuBoList = menuBoList;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


}
