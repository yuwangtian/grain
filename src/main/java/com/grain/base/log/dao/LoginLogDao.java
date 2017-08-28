package com.grain.base.log.dao;

import java.util.List;

import com.grain.base.log.bo.LoginLogBo;
import org.springframework.stereotype.Repository;

import com.grain.base.dao.BaseDao;

/**
 * 登录日志DAO
 *
 * @author Administrator
 */
@Repository
public interface LoginLogDao extends BaseDao {

    /**
     * 写入数据
     *
     * @param loginLogBo
     */
    public void insert(LoginLogBo loginLogBo);

    /**
     * 获取登录时间
     *
     * @param sessionId
     * @param userId
     * @return
     */
    public String getLoginDate(String sessionId, int userId);

    /**
     * 获取未正常退出的记录
     *
     * @param userId
     * @return
     */
    public List<LoginLogBo> getNotLogOut(int userId);

    /**
     * 更新登录状态
     *
     * @param loginLogBo
     */
    public void updateLoginState(LoginLogBo loginLogBo);

}
