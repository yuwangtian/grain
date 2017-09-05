package com.grain.sysconfig.user.service;

import com.grain.sysconfig.user.bo.LiYueBo;
import com.grain.sysconfig.user.bo.MeetingBo;
import com.grain.sysconfig.user.bo.UserBo;
import com.grain.sysconfig.user.dao.UserDao;
import com.grain.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserDao userDao;

    /**
     * 通过部门ID获取人员(已受浸)
     *
     * @param groupId
     * @return
     */
    public List<UserBo> getSaitsUserBoByGroupId(String groupId) {
        return userDao.getSaitsUserBoByGroupId(groupId);
    }

    /**
     * 通过部门ID获取人员(已受浸)
     *
     * @param groupId
     * @return
     */
    public List<UserBo> getLiYueUserBosByGroupId(String groupId, String meetingId) {
        return userDao.getLiYueUserBosByGroupId(groupId, meetingId);
    }

    /**
     * 通过部门ID获取人员(福音朋友)
     *
     * @param groupId
     * @return
     */
    public List<UserBo> getFriendsUserBoByGroupId(String groupId) {
        return userDao.getFriendsUserBoByGroupId(groupId);
    }

    /**
     * 通过部门ID获取人员(今年本地新得救圣徒)
     *
     * @param groupId
     * @return
     */
    public List<UserBo> getSaitsUserBoByTimeAndGroupId(String groupId) {
        return userDao.getSaitsUserBoByTimeAndGroupId(groupId);
    }


    /**
     * 删除人员
     *
     * @param user_id
     * @return
     */
    public void delUser(String user_id) {
        userDao.delUser(user_id);
    }

    /**
     * 通过ID获取人员
     *
     * @param user_id
     * @return
     */
    public UserBo getUserBoByUserId(String user_id) {
        return userDao.getUserBoByUserId(user_id);
    }

    /**
     * 通过部门ID获取聚会的人员
     *
     * @param groupId
     * @return
     */
    public List<UserBo> getMeetingUserBoByGroupId(String groupId, String meetingId) {
        Date beginDate;
        Date endDate;
        Date date = new Date();

        int dayOfWeek = DateUtils.dayOfWeek(date);
        if (dayOfWeek < 3) {
            int lastWeek = 7 - dayOfWeek;
            //周日，周一，
            //获取上周的数据
            beginDate = DateUtils.getPastDate(date, lastWeek);
        } else {
            int thisWeek = dayOfWeek - 3;
            //周二、周三、周四、周五、周六 获取本周数据
            beginDate = DateUtils.getPastDate(date, thisWeek);
        }
        //结束日期为开始日期的后7天
        endDate = DateUtils.getFetureDate(beginDate, 7);
        return userDao.getMeetingUserBoByGroupId(groupId, meetingId, beginDate, endDate);
    }


    /**
     * 参加聚会
     *
     * @param user_id
     * @param meeting_id
     * @param attendtime
     * @return
     */
    public void attendMeeting(String user_id, int meeting_id, Date attendtime) {
        userDao.attendMeeting(user_id, meeting_id, attendtime);
    }

    /**
     * 未参加聚会
     *
     * @param user_id
     * @param meeting_id
     * @return
     */
    public void notAttendMeeting(String user_id, int meeting_id) {
        userDao.notAttendMeeting(user_id, meeting_id);
    }

    /**
     * 受浸
     *
     * @param user_id
     * @param shoujin_flag
     * @return
     */
    public void shoujin(String user_id, String shoujin_flag) {
        UserBo userBo = userDao.getUserBoByUserId(user_id);
        if (userBo != null) {
            userBo.setShoujin_time(DateUtils.getCurrentDate());
        }
        if ("true".equals(shoujin_flag)) {
            userBo.setShoujin_flag("1");
        } else {
            userBo.setShoujin_flag("0");
        }
        userDao.updateUser(userBo);
    }

    /**
     * 增加人员
     *
     * @param userBo
     * @return
     */
    public void addUser(UserBo userBo) {
        userDao.addUser(userBo);
    }

    /**
     * 修改人员
     *
     * @param userBo
     * @return
     */
    public void updateUser(UserBo userBo) {
        userDao.updateUser(userBo);
    }

    /**
     * 增加福音碰哟
     *
     * @param userBo
     * @return
     */
    public void addFriend(UserBo userBo) {
        userDao.addFriend(userBo);
    }


    /**
     * 获取所有的聚会
     *
     * @return
     */
    public List<MeetingBo> getMeetings() {
        return userDao.getMeetings();
    }

    /**
     * 获取制定的聚会
     *
     * @return
     */
    public MeetingBo getMeetingByMeeting_id(String meeting_id) {
        return userDao.getMeetingByMeeting_id(meeting_id);
    }


    /**
     * 增加立约
     *
     * @param
     * @return
     */
    public void addLiYue(List<LiYueBo> liYueBos) {
        for (LiYueBo liYueBo : liYueBos) {
            userDao.addLiYue(liYueBo);
        }
    }
}
