package com.grain.sysconfig.user.dao;

import com.grain.base.dao.BaseDao;
import com.grain.sysconfig.user.bo.LiYueBo;
import com.grain.sysconfig.user.bo.MeetingBo;
import com.grain.sysconfig.user.bo.UserBo;
import com.grain.utils.pageutils.LimitInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public interface UserDao extends BaseDao {
    /**
     *  已经得救的圣徒
     * 通过部门ID获取人员
     * @return
     */
     List<UserBo> getSaitsUserBoByGroupId(String groupId);

    /**
     *  已立约的人员
     * 通过部门ID获取人员
     * @return
     */
    List<UserBo> getLiYueUserBosByGroupId(String groupId,String meetingId);



    /**
     * 福音朋友
     * 通过部门ID获取人员
     * @return
     */
     List<UserBo> getFriendsUserBoByGroupId(String groupId);

    /**
     * 今年本地新得救圣徒
     * 通过部门ID获取人员
     * @return
     */
     List<UserBo> getSaitsUserBoByTimeAndGroupId(String groupId);


    /**
     * 获取久不聚会的弟兄姊妹
     *
     * @param groupId
     * @return
     */
     List<UserBo> getJiuBuJuHuiSaitsUserBoByGroupId(String groupId,Date beginDate,Date endDate);

    /**
     * 通过Id找用户
     * @param groupId
     * @return
     */
    UserBo getUserBoByUserId(String groupId);

    /**
     * 通过部门ID获取人员(聚会人数)
     * @param groupId
     * @return
     */
    List<UserBo>  getMeetingUserBoByGroupId(String groupId,String meetingId, Date beginDate, Date endDate);


    /**
     *    一段时间内的所有聚会人员(聚会人数)
     * @return
     */
    List<UserBo>  getMeetingUserBos( Date beginDate, Date endDate);

    /**
     * 参加聚会
     * @param user_id
     * @param meeting_id
     * @param attendtime
     * @return
     */
     void attendMeeting(String user_id,int meeting_id,Date attendtime);

    /**
     * 删除人员
     * @param user_id
     * @return
     */
    void delUser(String user_id);

    /**
     * 未参加聚会
     * @param user_id
     * @param meeting_id
     * @return
     */
     void notAttendMeeting(String user_id,int meeting_id, Date attendtime);


    /**
     * 增加人员
     * @param userBo
     * @return
     */
    void addUser(UserBo userBo);
    /**
     * 修改人员
     * @param userBo
     * @return
     */
     void updateUser(UserBo userBo);

    /**
     * 增加福音朋友
     * @param userBo
     * @return
     */
    void addFriend(UserBo userBo);

    /**
     * 获取所有的聚会
     * @return
     */
     List<MeetingBo> getMeetings();

    /**
     * 获取制定的聚会
     * @return
     */
     MeetingBo getMeetingByMeeting_id(String meeting_id);


    /**
     * 增加立约
     * @param
     * @return
     */
     void addLiYue(LiYueBo liYueBo);

}

