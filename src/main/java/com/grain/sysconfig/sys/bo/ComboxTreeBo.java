package com.grain.sysconfig.sys.bo;

import java.util.List;

public class ComboxTreeBo {

    private Integer id;

    private String text;

    private List<ComboxTreeBo> children;

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

    public List<ComboxTreeBo> getChildren() {
        return children;
    }

    public void setChildren(List<ComboxTreeBo> children) {
        this.children = children;
    }


}
