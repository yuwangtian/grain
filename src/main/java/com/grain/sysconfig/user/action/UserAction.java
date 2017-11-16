package com.grain.sysconfig.user.action;

import com.alibaba.fastjson.JSON;
import com.grain.base.GroupAndTypeBo;
import com.grain.base.action.BaseAction;
import com.grain.base.log.AppLogService;
import com.grain.base.log.bo.OperateLogBo;
import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.sysconfig.sys.service.GroupService;
import com.grain.sysconfig.user.bo.LiYueBo;
import com.grain.sysconfig.user.bo.MeetingBo;
import com.grain.sysconfig.user.bo.UserBo;
import com.grain.sysconfig.user.service.UserService;
import com.grain.utils.DateUtils;
import com.grain.utils.cache.CachePara;
import com.grain.utils.cache.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class UserAction extends BaseAction {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @Autowired
    AppLogService appLogService;

    /**
     * 用户列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/userList")
    public String userList(
            HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo = this.common(request, response);
        this.queryUserBoList(request, groupAndTypeBo.getGroupBo(), groupAndTypeBo.getType());
        return "/userList";
    }

    /**
     * 查询并设置
     *
     * @param request
     * @param groupBo
     */
    private void queryUserBoList(HttpServletRequest request, GroupBo groupBo, String type) {
        List<UserBo> userBoList = null;
        String typeName = "";
        if ("saits_total_num".equals(type)) {
            userBoList = userService.getSaitsUserBoByGroupId(groupBo.getGroup_id());
            typeName = "总名单";
        } else if ("friends_num".equals(type)) {
            userBoList = userService.getFriendsUserBoByGroupId(groupBo.getGroup_id());
            typeName = "福音名单";
        } else if ("new_saits_total_num".equals(type)) {
            userBoList = userService.getSaitsUserBoByTimeAndGroupId(groupBo.getGroup_id());
            typeName = "受浸名单";
        } else if ("meeting".equals(type)) {
            String meeting_id = request.getParameter("meeting_id");
            MeetingBo meetingBo = userService.getMeetingByMeeting_id(meeting_id);
            typeName = meetingBo.getMeeting_name();
            userBoList = userService.getMeetingUserBoByGroupId(request,groupBo.getGroup_id(), meeting_id);
        }
        request.setAttribute("userBoList", userBoList);
        request.setAttribute("typeName", typeName);
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/operate")
    public String operate(
            HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo = this.common(request, response);
        return this.returnOperate(request, groupAndTypeBo.getGroupBo(), groupAndTypeBo.getType());
    }

    /**
     * 转到立约的页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/gotoLiyue")
    public String gotoLiyue(
            HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo = this.common(request, response);
        String meeting_id = request.getParameter("meeting_id");
        List<GroupBo> groupBos = groupService.getChildsGroupBoByGroupId(groupAndTypeBo.getGroupBo().getGroup_id());
        request.setAttribute("groupBos", groupBos);
        request.setAttribute("meeting_id", meeting_id);
        return "/liyue";
    }

    /**
     * 查询并设置
     *
     * @param request
     * @param groupBo
     */
    private String returnOperate(HttpServletRequest request, GroupBo groupBo, String type) {
        List<UserBo> userBoList = null;
        String typeName = "";
        String returnPate = "";
        String meeting_id = request.getParameter("meeting_id");
        if ("saits_total_num".equals(type)) {
            List<GroupBo> groupBos = groupService.getChildsGroupBoByGroupId(groupBo.getGroup_id());
            typeName = "总名单";
            request.setAttribute("groupBos", groupBos);
            returnPate = "/addSait";
        } else if ("friends_num".equals(type)) {
            List<GroupBo> groupBos = groupService.getChildsGroupBoByGroupId(groupBo.getGroup_id());
            request.setAttribute("groupBos", groupBos);
            typeName = "福音名单";
            returnPate = "/addFriend";
        } else if ("new_saits_total_num".equals(type)) {
            List<UserBo> friends = userService.getFriendsUserBoByGroupId(groupBo.getGroup_id());
            List<UserBo> newSaits = userService.getSaitsUserBoByTimeAndGroupId(groupBo.getGroup_id());
            userBoList = new ArrayList<>();
            for (UserBo allUserBo : friends) {
                if (!newSaits.contains(allUserBo)) {
                    allUserBo.setShoujin_flag("0");
                    userBoList.add(allUserBo);
                }
            }
            for (UserBo userBo : newSaits) {
                userBo.setShoujin_flag("1");
                userBoList.add(userBo);
            }
            typeName = "受浸名单";
            returnPate = "/shoujin";
        } else if ("meeting".equals(type)) {
            returnPate = "/meetingUserList";
            MeetingBo meetingBo = userService.getMeetingByMeeting_id(meeting_id);
            typeName = meetingBo.getMeeting_name();

            List<UserBo> allBoList = userService.getSaitsUserBoByGroupId(groupBo.getGroup_id());
            List<UserBo> meetingList = userService.getMeetingUserBoByGroupId(request,groupBo.getGroup_id(), meeting_id);
            userBoList = new ArrayList<>();
            for (UserBo allUserBo : allBoList) {
                if (!meetingList.contains(allUserBo)) {
                    allUserBo.setLord_flag("0");
                    userBoList.add(allUserBo);
                }
            }
            for (UserBo userBo : meetingList) {
                userBo.setLord_flag("1");
                userBoList.add(userBo);
            }
        } else if ("meeting_liyue".equals(type)) {
            returnPate = "/meetingUserList";
            MeetingBo meetingBo = userService.getMeetingByMeeting_id(meeting_id);
            typeName = meetingBo.getMeeting_name();

//            meeting_liyue
            List<UserBo> liyueBoList = userService.getLiYueUserBosByGroupId(groupBo.getGroup_id(), meeting_id);
            List<UserBo> meetingList = userService.getMeetingUserBoByGroupId(request,groupBo.getGroup_id(), meeting_id);
            userBoList = new ArrayList<>();
            for (UserBo allUserBo : liyueBoList) {
                if (!meetingList.contains(allUserBo)) {
                    allUserBo.setLord_flag("0");
                    userBoList.add(allUserBo);
                }
            }
            for (UserBo userBo : meetingList) {
                userBo.setLord_flag("1");
                userBoList.add(userBo);
            }
        }
        request.setAttribute("userBoList", userBoList);
        request.setAttribute("typeName", typeName);
        request.setAttribute("meeting_id", meeting_id);
        return returnPate;
    }

    /**
     * 公共
     *
     * @param request
     * @param response
     */
    private GroupAndTypeBo common(
            HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo = new GroupAndTypeBo();
        GroupBo groupBo = null;
        String type = null;
        String groupId = request.getParameter("groupId");
        type = request.getParameter("type");
        if (groupId != null) {
            groupBo = groupService.getGroupBoByGroupId(groupId);
        }
        GroupBo session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        if (groupBo == null) {
            groupBo = session;
        }
        if (groupBo != null) {
            request.setAttribute("loginName", groupBo.getName());
            request.setAttribute("groupId", groupBo.getGroup_id());
            request.setAttribute("sessionName", session.getName());
        }
        groupAndTypeBo.setGroupBo(groupBo);
        groupAndTypeBo.setType(type);
        return groupAndTypeBo;
    }


    /**
     * 删除人
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "delUser", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String delUser(HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo = this.common(request, response);
        OperateLogBo operateLogBo = new OperateLogBo();
        operateLogBo.setOperate_time(DateUtils.getCurrentDateTime());
        operateLogBo.setOperate_group_id(groupAndTypeBo.getGroupBo().getGroup_id());
        operateLogBo.setOperate_group_name(groupAndTypeBo.getGroupBo().getName());
        operateLogBo.setOperate_type("删除用户");
        appLogService.insertLog(operateLogBo);
        String user_id = request.getParameter("user_id");
        userService.delUser(user_id);
        return "success";
    }

    /**
     * 聚会签到
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "operateMeeting", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String operateMeeting(HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo = this.common(request, response);
        String user_id = request.getParameter("user_id");
        String isAttended = request.getParameter("isAttended");
        String meeting_id = request.getParameter("meeting_id");
        if (user_id == null || isAttended == null) {
            return "fail";
        }

        Date date = null;
        MeetingBo meetingBo = userService.getMeetingByMeeting_id(meeting_id);
        if (StringUtils.isEmpty(meetingBo.getDay_of_week())) {
            date = DateUtils.currentDate();
        } else {
            date = new Date();
            int dayOfWeek = DateUtils.dayOfWeek(date);
            if (dayOfWeek < 3) {
                int lastWeek =dayOfWeek+7-Integer.parseInt(meetingBo.getDay_of_week());//上周
//                int lastWeek = Integer.parseInt(meetingBo.getDay_of_week()) - dayOfWeek;
                //周日，周一，
                //获取上周的数据
                date = DateUtils.getPastDate(date, lastWeek);
            } else {
                int thisWeek = Integer.parseInt(meetingBo.getDay_of_week()) - dayOfWeek;//本周
                //周二、周三、周四、周五、周六 获取本周数据
                date = DateUtils.getFetureDate(date, thisWeek);
            }
//            date = DateUtils.getDayOfWeek(Integer.parseInt(meetingBo.getDay_of_week()));
        }

        OperateLogBo operateLogBo = new OperateLogBo();
        operateLogBo.setOperate_time(DateUtils.getCurrentDateTime());
        GroupBo session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        operateLogBo.setOperate_group_id(session.getGroup_id());
        operateLogBo.setOperate_group_name(session.getName());
        operateLogBo.setOperate_type("聚会签到");
        appLogService.insertLog(operateLogBo);

        if ("true".equals(isAttended)) {
            userService.notAttendMeeting(user_id, Integer.parseInt(meeting_id),date);
            userService.attendMeeting(user_id, Integer.parseInt(meeting_id), date);
            //add;
        }
        if ("false".equals(isAttended)) {
            userService.notAttendMeeting(user_id, Integer.parseInt(meeting_id),date);
        }
        return "success";
    }


    /**
     * 受浸
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "shoujin", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String shoujin(HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo =this.common(request, response);
        String user_id = request.getParameter("user_id");
        String isShoujin = request.getParameter("isShoujin");
        if (user_id == null || isShoujin == null) {
            return "fail";
        }
        GroupBo session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        OperateLogBo operateLogBo = new OperateLogBo();
        operateLogBo.setOperate_time(DateUtils.getCurrentDateTime());
        operateLogBo.setOperate_group_id(session.getGroup_id());
        operateLogBo.setOperate_group_name(session.getName());
        operateLogBo.setOperate_type("受浸或取消受浸"+isShoujin);
        appLogService.insertLog(operateLogBo);
        userService.shoujin(user_id, isShoujin);
        return "success";
    }

    /**
     * 增加福音朋友
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("addFriend")
    public String addFriend(HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo =this.common(request, response);
        UserBo userBo = new UserBo();
        String name = request.getParameter("name");
        userBo.setName(name);
        String user_id = request.getParameter("user_id");
        if (StringUtils.isNotEmpty(user_id)) {
            userBo.setUser_id(Integer.parseInt(user_id));
        }
        String age = request.getParameter("age");
        userBo.setAge(age);
        String sex = request.getParameter("sex");
        userBo.setSex(Integer.parseInt(sex));
        userBo.setShoujin_local_flag("1");
        String group_id = request.getParameter("group_id");
        userBo.setGroup_id(Integer.parseInt(group_id));
        String remark = request.getParameter("remark");
        if (remark == null) {
            remark = " ";
        }
        userBo.setRemark(remark);
        userBo.setShoujin_flag("0");
        GroupBo groupBo = groupService.getGroupBoByGroupId(group_id);
        userBo.setGroup_code(groupBo.getCode());

        if (StringUtils.isEmpty(user_id)) {
            userService.addFriend(userBo);
        } else {
            userService.updateUser(userBo);
        }
        GroupBo session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        OperateLogBo operateLogBo = new OperateLogBo();
        operateLogBo.setOperate_time(DateUtils.getCurrentDateTime());
        operateLogBo.setOperate_group_id(session.getGroup_id());
        operateLogBo.setOperate_group_name(session.getName());
        operateLogBo.setOperate_type("增加或修改福音朋友");
        appLogService.insertLog(operateLogBo);
        return "redirect:" + REDIRECT_URL + "/userList.do?groupId=" + groupBo.getGroup_id() + "&type=friends_num";
    }

    /**
     * 增加圣徒
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("addSait")
    public String addSait(HttpServletRequest request, HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo =this.common(request, response);
        UserBo userBo = new UserBo();
        String name = request.getParameter("name");
        userBo.setName(name);
        String age = request.getParameter("age");
        userBo.setAge(age);
        String user_id = request.getParameter("user_id");
        if (StringUtils.isNotEmpty(user_id)) {
            userBo.setUser_id(Integer.parseInt(user_id));
        }
        String sex = request.getParameter("sex");
        userBo.setSex(Integer.parseInt(sex));
        String shoujin_time = request.getParameter("shoujin_time");


        String shoujin_local_flag = request.getParameter("shoujin_local_flag");
        userBo.setShoujin_local_flag(shoujin_local_flag);
        String group_id = request.getParameter("group_id");
        userBo.setGroup_id(Integer.parseInt(group_id));
        String remark = request.getParameter("remark");
        if (remark == null) {
            remark = " ";
        }
        userBo.setRemark(remark);
        userBo.setShoujin_flag("1");
        GroupBo groupBo = groupService.getGroupBoByGroupId(group_id);
        userBo.setGroup_code(groupBo.getCode());
        if (!StringUtils.isEmpty(shoujin_time)) {
            userBo.setShoujin_time(shoujin_time);
        }
        if (StringUtils.isEmpty(user_id)) {
            userService.addUser(userBo);
        } else {
            userService.updateUser(userBo);
        }
        GroupBo session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        OperateLogBo operateLogBo = new OperateLogBo();
        operateLogBo.setOperate_time(DateUtils.getCurrentDateTime());
        operateLogBo.setOperate_group_id(session.getGroup_id());
        operateLogBo.setOperate_group_name(session.getName());
        operateLogBo.setOperate_type("增加或修改弟兄姊妹");
        appLogService.insertLog(operateLogBo);
        return "redirect:" + REDIRECT_URL + "/userList.do?groupId=" + groupBo.getGroup_id() + "&type=saits_total_num";
    }

    /**
     * 修改用户
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/gotoAddUser")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo = this.common(request, response);
        String returnPage = "";
        if ("addSait".equals(groupAndTypeBo.getType())) {
            returnPage = "/addSait";
        } else if ("addFriend".equals(groupAndTypeBo.getType())) {
            returnPage = "/addFriend";
        }
        List<GroupBo> groupBos = groupService.getAllSmallGroups(request);
        request.setAttribute("groupBos", groupBos);
        String user_id = request.getParameter("user_id");
        UserBo userBo = userService.getUserBoByUserId(user_id);
        try {
            if (!StringUtils.isEmpty(userBo.getRemark())) {
                userBo.setRemark(java.net.URLEncoder.encode(userBo.getRemark(), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.setAttribute("userBo", userBo);
        GroupBo session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        OperateLogBo operateLogBo = new OperateLogBo();
        operateLogBo.setOperate_time(DateUtils.getCurrentDateTime());
        operateLogBo.setOperate_group_id(session.getGroup_id());
        operateLogBo.setOperate_group_name(session.getName());
        operateLogBo.setOperate_type("准备修改用户");
        appLogService.insertLog(operateLogBo);
        return returnPage;
    }

    /**
     * 通过部门ID获取用户
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getUsersByGroupId", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getUsersByGroupId(HttpServletRequest request, HttpServletResponse response) {
        this.common(request, response);
        String group_id = request.getParameter("group_id");
        List<UserBo> list = userService.getSaitsUserBoByGroupId(group_id);
        String json = JSON.toJSONString(list);
        return json;
    }

    /**
     * 通过部门ID获取用户
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getAllSmallGroups", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getAllSmallGroups(HttpServletRequest request, HttpServletResponse response) {
        this.common(request, response);
        List<GroupBo> groupBos = groupService.getAllSmallGroups(request);
        String json = JSON.toJSONString(groupBos);
        return json;
    }


    /**
     * 立约
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/liyue")
    public String liyue(HttpServletRequest request,
                        HttpServletResponse response) {
        GroupAndTypeBo groupAndTypeBo = this.common(request, response);
        String meeting_id = request.getParameter("meeting_id");
        long time = System.currentTimeMillis();
        String li_yue_id = time + "";
        List<LiYueBo> liYueBoList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            String user_id = request.getParameter("users" + i);
            String user_li_yue_type = request.getParameter("user_li_yue_type" + i);
            String remark = request.getParameter("remark" + i);
            System.out.println();
            if (StringUtils.isNotEmpty(user_id) && StringUtils.isNotEmpty(user_li_yue_type)) {
                LiYueBo liYueBo = new LiYueBo();
                liYueBo.setLi_yue_id(li_yue_id);
                liYueBo.setMeeting_id(Integer.parseInt(meeting_id));
                liYueBo.setUser_li_yue_type(Integer.parseInt(user_li_yue_type));
                liYueBo.setRemark(remark);
                liYueBo.setUser_id(Integer.parseInt(user_id));
                liYueBo.setSort_num(i);
                liYueBoList.add(liYueBo);
            }

        }
        userService.addLiYue(liYueBoList);
        GroupBo session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        OperateLogBo operateLogBo = new OperateLogBo();
        operateLogBo.setOperate_time(DateUtils.getCurrentDateTime());
        operateLogBo.setOperate_group_id(session.getGroup_id());
        operateLogBo.setOperate_group_name(session.getName());
        operateLogBo.setOperate_type("立约");
        appLogService.insertLog(operateLogBo);
        return "redirect:" + REDIRECT_URL + "/operate.do?groupId=" + groupAndTypeBo.getGroupBo().getGroup_id() + "&type=meeting_liyue&meeting_id=" + meeting_id;
    }

}