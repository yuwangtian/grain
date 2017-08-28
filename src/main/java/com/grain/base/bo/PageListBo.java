package com.grain.base.bo;

import java.util.List;
import java.util.Map;

/**
 * @author yuchen
 */
public class PageListBo {

    //add by jijun 20170206
    //用于记录我的订单中的订单总数,被驳回订单数,已分配订单数,投放中的订单数
    private Map<String, Integer> pageInfoMap;

    public PageListBo(List rows, Integer total) {
        this.rows = rows;
        this.total = total;


    }

    /**
     *
     */
    private List<BaseBo> rows;
    /**
     *
     */
    private Integer total;

    public List<BaseBo> getRows() {
        return rows;
    }

    public void setRows(List<BaseBo> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Map<String, Integer> getPageInfoMap() {
        return pageInfoMap;
    }

    public void setPageInfoMap(Map<String, Integer> pageInfoMap) {
        this.pageInfoMap = pageInfoMap;
    }

}