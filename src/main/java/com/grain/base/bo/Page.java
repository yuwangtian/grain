package com.grain.base.bo;

import java.util.List;

/**
 * @author anqi
 * @since 2014/6/26.
 */
public class Page<T> {
    private int pageSize;//每页记录数
    private int currentPage;//当前页
    private int totalPage;//总页数
    private int totalItem;//总记录
    private int start;

    private List<T> list;

    public Page() {
        pageSize = 20;
        currentPage = 1;
        totalPage = 1;
        totalItem = 0;
        start = 0;
    }

    public void setup() {
        if (pageSize < 1) pageSize = 20;
        if (totalItem % pageSize > 0) {
            totalPage = totalItem / pageSize + 1;
        } else {
            totalPage = totalItem / pageSize;
        }
        if (totalPage == 0) {
            totalPage = 1;
        }
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        if (currentPage < 1) {
            currentPage = 1;
        }
        start = pageSize * (currentPage - 1);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getStart() {
        return start;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
