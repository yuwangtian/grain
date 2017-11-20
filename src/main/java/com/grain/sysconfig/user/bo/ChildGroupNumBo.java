package com.grain.sysconfig.user.bo;

import com.grain.base.bo.BaseBo;

import java.util.List;

/**
 * Created by yuchen
 * on 2017/8/16 0016.
 */
public class ChildGroupNumBo extends BaseBo {
    /**
     * 部门ID
     */
    private String group_id;
    /**
     * 部门名称
     */
    private String group_name;
    /**
     * 部门 编号
     */
    private String group_code;

    /**
     * 圣徒总人数
     */
    private int saits_total_num;
    /**
     * 福音朋友人数
     */
    private int friends_num;
    /**
     * 新受浸的圣徒
     */
    private int new_saits_total_num;

    /**
     * 久不聚会的圣徒
     */
    private int jbjh_saits_num;

    public int getJbjh_saits_num() {
        return jbjh_saits_num;
    }

    public void setJbjh_saits_num(int jbjh_saits_num) {
        this.jbjh_saits_num = jbjh_saits_num;
    }

    /**
     * 聚会情况
     */
    private List<MeetingBo> meetingBoList;

    public List<MeetingBo> getMeetingBoList() {
        return meetingBoList;
    }

    public void setMeetingBoList(List<MeetingBo> meetingBoList) {
        this.meetingBoList = meetingBoList;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public int getSaits_total_num() {
        return saits_total_num;
    }

    public void setSaits_total_num(int saits_total_num) {
        this.saits_total_num = saits_total_num;
    }

    public int getFriends_num() {
        return friends_num;
    }

    public void setFriends_num(int friends_num) {
        this.friends_num = friends_num;
    }

    public int getNew_saits_total_num() {
        return new_saits_total_num;
    }

    public void setNew_saits_total_num(int new_saits_total_num) {
        this.new_saits_total_num = new_saits_total_num;
    }

}
