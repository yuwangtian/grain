package com.grain.base.log.imp;

import java.util.List;

import com.grain.base.log.bo.LoginLogBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grain.base.log.dao.LoginLogDao;

/**
 * 登录日志Log
 *
 * @author Administrator
 */
@Service
public class LoginLogService {

    @Autowired
    private LoginLogDao dao;

    /**
     * 写入数据
     *
     * @param loginLogBo
     */
    public void insert(LoginLogBo loginLogBo) {
        dao.insert(loginLogBo);
    }

    /**
     * 获取登录时间
     *
     * @param sessionId
     * @param userId
     * @return
     */
    public String getLoginDate(String sessionId, int userId) {
        return dao.getLoginDate(sessionId, userId);
    }

    /**
     * 获取未正常退出的记录
     *
     * @param userId
     * @return
     */
    public List<LoginLogBo> getNotLogOut(int userId) {
        return dao.getNotLogOut(userId);
    }

    /**
     * 更新登录状态
     *
     * @param loginLogBo
     */
    public void updateLoginState(LoginLogBo loginLogBo) {
        dao.updateLoginState(loginLogBo);
    }

}
