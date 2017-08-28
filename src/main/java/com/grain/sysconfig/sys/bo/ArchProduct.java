package com.grain.sysconfig.sys.bo;

import java.util.Date;

/**
 * 产品表
 *
 * @author wzy
 * @since 2014-11-21 09:52:15
 */
public class ArchProduct {
    private Integer product_id;//产品id
    private String en_code;//英文名称
    private String product_name;//产品名称
    private Date create_time;//创建时间
    private Integer create_user_id;//创建人id
    private Date last_edited_time;//最后修改时间
    private Integer last_edit_user_id;//最后修改人
    private Integer valid_falg;//有效标志 1：有效 0：无效
    private Integer del_flag;//删除标志
    private String productIds;

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public Date getLast_edited_time() {
        return last_edited_time;
    }

    public void setLast_edited_time(Date last_edited_time) {
        this.last_edited_time = last_edited_time;
    }

    public Integer getLast_edit_user_id() {
        return last_edit_user_id;
    }

    public void setLast_edit_user_id(Integer last_edit_user_id) {
        this.last_edit_user_id = last_edit_user_id;
    }

    public Integer getValid_falg() {
        return valid_falg;
    }

    public void setValid_falg(Integer valid_falg) {
        this.valid_falg = valid_falg;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getEn_code() {
        return en_code;
    }

    public void setEn_code(String en_code) {
        this.en_code = en_code;
    }


}
