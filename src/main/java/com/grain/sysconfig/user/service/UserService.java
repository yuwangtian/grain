package com.grain.sysconfig.user.service;

import com.grain.sysconfig.user.bo.LiYueBo;
import com.grain.sysconfig.user.bo.MeetingBo;
import com.grain.sysconfig.user.bo.UserBo;
import com.grain.sysconfig.user.dao.UserDao;
import com.grain.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class UserService {


    @Autowired
    private UserDao userDao;

    /**
     * 通过部门ID获取人员(已受浸)
     * @param groupId
     * @return
     */
    public List<UserBo> getSaitsUserBoByGroupId(String groupId){
      return   userDao.getSaitsUserBoByGroupId(groupId);
    }
    /**
     * 通过部门ID获取人员(福音朋友)
     * @param groupId
     * @return
     */
    public List<UserBo> getFriendsUserBoByGroupId(String groupId){
        return   userDao.getFriendsUserBoByGroupId(groupId);
    }

    /**
     * 通过部门ID获取人员(今年本地新得救圣徒)
     * @param groupId
     * @return
     */
    public List<UserBo> getSaitsUserBoByTimeAndGroupId(String groupId){
        return   userDao.getSaitsUserBoByTimeAndGroupId(groupId);
    }

    /**
     * 通过ID获取人员
     * @param user_id
     * @return
     */
    public UserBo getUserBoByUserId(String user_id){
        return   userDao.getUserBoByUserId(user_id);
    }

    /**
     * 通过部门ID获取聚会的人员
     * @param groupId
     * @return
     */
    public List<UserBo> getMeetingUserBoByGroupId(String groupId,String meetingId){
        return   userDao.getMeetingUserBoByGroupId(groupId,meetingId);
    }

    /**
     * 参加聚会
     * @param user_id
     * @param meeting_id
     * @param attendtime
     * @return
     */
    public void attendMeeting(String user_id,int meeting_id,Date attendtime){
           userDao.attendMeeting( user_id, meeting_id, attendtime);
    }

    /**
     * 未参加聚会
     * @param user_id
     * @param meeting_id
     * @return
     */
    public void notAttendMeeting(String user_id,int meeting_id){
           userDao.notAttendMeeting( user_id, meeting_id);
    }

    /**
     * 受浸
     * @param user_id
     * @param shoujin_flag
     * @return
     */
    public void shoujin(String user_id,String shoujin_flag){
        if("true".equals(shoujin_flag)){
            userDao.shoujin( user_id);
        }else{
            userDao.notShoujin( user_id);
        }
    }
    /**
     * 增加人员
     * @param userBo
     * @return
     */
    public void addUser(UserBo userBo){
            userDao.addUser(userBo);
    }
    /**
     * 修改人员
     * @param userBo
     * @return
     */
    public void updateUser(UserBo userBo){
        userDao.updateUser(userBo);
    }
    /**
     * 增加福音碰哟
     * @param userBo
     * @return
     */
    public void addFriend(UserBo userBo){
        userDao.addFriend(userBo);
    }


    /**
     * 获取所有的聚会
     * @return
     */
    public List<MeetingBo> getMeetings(){
        return   userDao.getMeetings();
    }

    /**
     * 获取制定的聚会
     * @return
     */
    public MeetingBo getMeetingByMeeting_id(String meeting_id){
        return   userDao.getMeetingByMeeting_id(meeting_id);
    }


    /**
     * 增加立约
     * @param
     * @return
     */
    public void addLiYue(List<LiYueBo> liYueBos){
        for(LiYueBo liYueBo:liYueBos){
            userDao.addLiYue(liYueBo);
        }
    }
}
