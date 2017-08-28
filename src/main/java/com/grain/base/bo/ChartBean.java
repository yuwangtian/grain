package com.grain.base.bo;

import java.util.List;

/**
 * 图形显示用
 *
 * @author wzy
 */
public class ChartBean {

    private String name;
    private List<Double> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

}
