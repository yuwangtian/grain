package com.grain.sysconfig.user.bo;

import com.grain.base.bo.BaseBo;

/**
 * Created by yuchen
 * on 2017/8/25 0025.
 */
public class LiYueBo extends BaseBo {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 一个签约小组的唯一标识
     */
    private String li_yue_id;
    /**
     * 一个签约的类型
     */
    private Integer meeting_id;
    /**
     * 立约人
     */
    private Integer user_id;
    /**
     * 人立约所属类型
     */
    private Integer user_li_yue_type;
    /**
     * 立约人的备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLi_yue_id() {
        return li_yue_id;
    }

    public void setLi_yue_id(String li_yue_id) {
        this.li_yue_id = li_yue_id;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_li_yue_type() {
        return user_li_yue_type;
    }

    public void setUser_li_yue_type(Integer user_li_yue_type) {
        this.user_li_yue_type = user_li_yue_type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
