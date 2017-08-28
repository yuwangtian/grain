package com.grain.base.log.bo;

import com.grain.base.bo.BaseBo;

/**
 * 登录日志表
 *
 * @author Administrator table : erp_login_log
 */
public class LoginLogBo extends BaseBo {

    private int userId;// 用户ID
    private String loginDate;// 登录/退出时间
    private float loginLong;// 本次登录时长
    private int loginType;// 1登录2退出
    private String sessionId;
    private int loginState;// 0已经退出登录1登录中

    public static final int TYPE_LOGIN = 1;
    public static final int TYPE_LOGINOUT = 2;
    public static final int STATE_LOGIN = 1;
    public static final int STATE_LOGINOUT = 0;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public float getLoginLong() {
        return loginLong;
    }

    public void setLoginLong(float loginLong) {
        this.loginLong = loginLong;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }
}
