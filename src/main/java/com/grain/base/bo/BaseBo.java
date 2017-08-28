package com.grain.base.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类BO
 *
 * @author yuchen
 * @since 2014-11-27
 */
public class BaseBo implements Serializable{

    /**
     * 创建日期
     */
    private Date create_time;

    /**
     * 创建人id
     */
    private Integer create_user_id;
    /**
     * 最后修改人id
     */
    private Integer last_edited_user_id;


    /**
     * 最后修改时间
     */
    private Date last_edited_time;
    /**
     * 有效标志 1：有效 0：无效
     */
    private Integer valid_flag;

    /**
     * 有效标志 有效
     */
    public static Integer VALID_FLAG_YES = 1;

    /**
     * 有效标志 无效
     */
    public static Integer VALID_FLAG_NO = 0;


    /**
     * 删除标志 1：已删除 0：未删除
     */
    private Integer del_flag;

    /**
     * 删除标志 已删除
     */
    public static Integer DEL_FLAG_YES = 1;

    /**
     * 删除标志 未删除
     */
    public static Integer DEL_FLAG_NO = 0;
    /**
     * 排序号 越小越靠前
     */
    private Integer sort_num;

    /**
     * 置为无效的时间
     */
    private Date invalid_time;
    /**
     * 删除的时间
     */
    private Date delete_time;


    /**
     * 页数
     */
    private Integer page = 1;
    //行数
    private Integer rows = 24;
    /**
     * 开始的行
     */
    private int startRow;
    /**
     * 每页的大小
     */
    private int pageSize;
    //-------------------


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

    public Integer getSort_num() {
        return sort_num;
    }

    public void setSort_num(Integer sort_num) {
        this.sort_num = sort_num;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void calStartRow() {
        this.startRow = rows * (page - 1);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
        this.pageSize = rows;
    }

    public Date getInvalid_time() {
        return invalid_time;
    }

    public void setInvalid_time(Date invalid_time) {
        this.invalid_time = invalid_time;
    }

    public Date getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Date delete_time) {
        this.delete_time = delete_time;
    }
}
