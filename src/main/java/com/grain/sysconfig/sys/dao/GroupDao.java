package com.grain.sysconfig.sys.dao;

import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.base.dao.BaseDao;
import com.grain.sysconfig.user.bo.UserBo;
import com.grain.utils.pageutils.LimitInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wzy
 * @since 2014-11-22 09:49:59
 */
@Repository
public interface GroupDao extends BaseDao {

    /**
     * 通过部门编号找部门
     *
     * @param code
     * @return
     */
    GroupBo getGroupBoByCode(String code);

    /**
     * 通过部门ID找部门
     *
     * @param groupId
     * @return
     */
    GroupBo getGroupBoByGroupId(String groupId);

    /**
     * 通过部门ID找父部门
     *
     * @param groupId
     * @return
     */
    GroupBo getParentGroupBoByGroupId(String groupId);


    /**
     * 通过部门找下一级子部门
     *
     * @param groupId
     * @return
     */
    List<GroupBo> getOneLevelChildGroupBoByGroupId(String groupId);

    /**
     * 通过部门找子部门
     *
     * @param groupId
     * @return
     */
    List<GroupBo> getChildsGroupBoByGroupId(String groupId);

    /**
     * 所有的小排
     */
    List<GroupBo> getAllSmallGroups(String groupCode);


}
