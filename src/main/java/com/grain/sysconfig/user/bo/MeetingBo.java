package com.grain.sysconfig.user.bo;

import com.grain.base.bo.BaseBo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuchen
 * on 2017/8/18 0018.
 */
public class MeetingBo extends BaseBo{
    /**
     * 聚会的ID
     */
    private String meeting_id;
    /**
     * 聚会的名称
     */
    private String meeting_name;
    /**
     * 本周第几天 周日为1，周六为7
     */
    private String day_of_week;

    /**
     * 聚会的频率
     */
    private String rate;
    /**
     * 需要立约的标识
     * 1：需要立约
     * 0：不需要立约
     */
    private String liyue_flag;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getLiyue_flag() {
        return liyue_flag;
    }

    public void setLiyue_flag(String liyue_flag) {
        this.liyue_flag = liyue_flag;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    /**
     * 聚会的人员
     */
    private List<UserBo> userBoList=new ArrayList<>();

    /**
     * 立约的人员
     */
    private List<UserBo> liYueUserBoList=new ArrayList<>();
    /**
     * 立约的人数
     */
    private int liYue_num;
    /**
     * 聚会的人数
     */
    private int meeting_num;
    /**
     * 聚会比例
     */
    private int meeting_percent;

    public List<UserBo> getLiYueUserBoList() {
        return liYueUserBoList;
    }

    public void setLiYueUserBoList(List<UserBo> liYueUserBoList) {
        this.liYueUserBoList = liYueUserBoList;
    }

    public int getLiYue_num() {
        return liYue_num;
    }

    public void setLiYue_num(int liYue_num) {
        this.liYue_num = liYue_num;
    }

    public List<UserBo> getUserBoList() {
        return userBoList;
    }

    public void setUserBoList(List<UserBo> userBoList) {
        this.userBoList = userBoList;
    }

    public int getMeeting_num() {
        return meeting_num;
    }

    public void setMeeting_num(int meeting_num) {
        this.meeting_num = meeting_num;
    }

    public int getMeeting_percent() {
        return meeting_percent;
    }

    public void setMeeting_percent(int meeting_percent) {
        this.meeting_percent = meeting_percent;
    }

    public String getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(String meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }
}
