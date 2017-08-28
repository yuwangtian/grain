package com.grain.base.bo.base;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Pmodule implements Serializable {
    private Integer page_id;
    private String page_name;
    private String eng_name;
    @JSONField(name = "pId")
    private Integer parent_id;
    private String page_url;
    private String page_img_url;//图片路径
    private Integer page_type;
    private List<Pmodule> children;
    private Integer sort_num;
    private Date create_time;
    private Integer create_user_id;
    private Integer last_edited_user_id;
    private Date last_edited_time;
    private Integer valid_flag;
    private Integer del_flag;

    private Integer three_page_id;//三级菜单Id

    //private boolean open;//设置是否打开


    public List<Pmodule> getChildren() {
        return children;
    }

    public void setChildren(List<Pmodule> children) {
        this.children = children;
    }

    public Integer getPage_id() {
        return page_id;
    }

    public void setPage_id(Integer page_id) {
        this.page_id = page_id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getPage_url() {
        return page_url;
    }

    public void setPage_url(String page_url) {
        this.page_url = page_url;
    }

    public String getPage_img_url() {
        return page_img_url;
    }

    public void setPage_img_url(String page_img_url) {
        this.page_img_url = page_img_url;
    }

    public Integer getSort_num() {
        return sort_num;
    }

    public void setSort_num(Integer sort_num) {
        this.sort_num = sort_num;
    }

    public Integer getPage_type() {
        return page_type;
    }

    public void setPage_type(Integer page_type) {
        this.page_type = page_type;
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

    public String getEng_name() {
        return eng_name;
    }

    public void setEng_name(String eng_name) {
        this.eng_name = eng_name;
    }

	/*public boolean isOpen() {
		return true;
	}

	public void setOpen(boolean open) {
		this.open = true;
	}*/

    public Integer getThree_page_id() {
        return three_page_id;
    }

    public void setThree_page_id(Integer three_page_id) {
        this.three_page_id = three_page_id;
    }
}
