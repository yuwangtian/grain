package com.grain.base.web;

import com.grain.base.action.BaseAction;
import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.sysconfig.sys.service.GroupService;
import com.grain.sysconfig.user.bo.ChildGroupNumBo;
import com.grain.sysconfig.user.bo.MeetingBo;
import com.grain.sysconfig.user.bo.UserBo;
import com.grain.sysconfig.user.service.UserService;
import com.grain.utils.PropertiesUtil;
import com.grain.utils.cache.CachePara;
import com.grain.utils.cache.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class LoginController extends BaseAction {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;


    /**
     * 首页
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public String login(
            HttpServletRequest request, HttpServletResponse response) {

        String groupId=request.getParameter("groupId");
        GroupBo groupBo=null;
        if(groupId!=null){
             groupBo =groupService.getGroupBoByGroupId(groupId);
        }
        GroupBo  session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        if(groupBo==null){
            groupBo = session;
        }
        if (groupBo != null) {
            this.queryNum(request, groupBo);
            request.setAttribute("loginName", groupBo.getName());
            GroupBo parentGroupBo=null;
            String groupShuoMing="";
            parentGroupBo= groupService.getParentGroupBoByGroupId(groupBo.getGroup_id());

            if("40".equals(groupBo.getGroup_level()+"")){
                groupShuoMing=parentGroupBo.getName()+"-";
                GroupBo grandparentGroupBo= groupService.getParentGroupBoByGroupId(parentGroupBo.getGroup_id());
                groupShuoMing=grandparentGroupBo.getName()+"-"+groupShuoMing;
            }
            if("30".equals(groupBo.getGroup_level()+"")){
                groupShuoMing=parentGroupBo.getName()+"-";
            }

            if(groupBo.getGroup_id().equals(session.getGroup_id())){
                parentGroupBo=session;
            }
            request.setAttribute("groupShuoMing", groupShuoMing);
            request.setAttribute("groupId", groupBo.getGroup_id());
            if(parentGroupBo!=null){
                request.setAttribute("sessionName", parentGroupBo.getName());
                request.setAttribute("sessionGroupId", parentGroupBo.getGroup_id());
            }else {
                request.setAttribute("sessionName", groupBo.getName());
                request.setAttribute("sessionGroupId", groupBo.getGroup_id());
            }

            return "/index";
        }
        return "redirect:"+REDIRECT_URL+"/login.do";
    }

    /**
     * 本级查询并设置
     *
     * @param request
     * @param groupBo
     */
    private void queryNum(HttpServletRequest request, GroupBo groupBo) {
        List<UserBo> saitsUserBoList = userService.getSaitsUserBoByGroupId(groupBo.getGroup_id());
        int saits_total_num = 0;
        if (saitsUserBoList != null && !saitsUserBoList.isEmpty()) {
            saits_total_num = saitsUserBoList.size();
        }
        request.setAttribute("saits_total_num", saits_total_num);
        List<UserBo> friendsUserBoList = userService.getFriendsUserBoByGroupId(groupBo.getGroup_id());
        int friends_num = 0;
        if (friendsUserBoList != null && !friendsUserBoList.isEmpty()) {
            friends_num = friendsUserBoList.size();
        }
        request.setAttribute("friends_num", friends_num);
        List<UserBo> newSaitsUserBoList = userService.getSaitsUserBoByTimeAndGroupId(groupBo.getGroup_id());
        int new_saits_total_num = 0;
        if (newSaitsUserBoList != null && !newSaitsUserBoList.isEmpty()) {
            new_saits_total_num = newSaitsUserBoList.size();
        }
        request.setAttribute("new_saits_total_num", new_saits_total_num);
        List<MeetingBo> meetingBoList=userService.getMeetings();
        for(MeetingBo meetingBo:meetingBoList){
            List<UserBo> userList = userService.getMeetingUserBoByGroupId(groupBo.getGroup_id(),meetingBo.getMeeting_id());
            int meeting_num = 0;
            if (userList != null && !userList.isEmpty()) {
                meeting_num = userList.size();
            }
            meetingBo.setMeeting_num(meeting_num);
            meetingBo.setUserBoList(userList);
            if ("1".equals(meetingBo.getLiyue_flag())) {
                List<UserBo> liYueList=userService.getLiYueUserBosByGroupId(groupBo.getGroup_id(),meetingBo.getMeeting_id());
                meetingBo.setLiYueUserBoList(liYueList);
                meetingBo.setLiYue_num(liYueList.size());
            }
            int meeting_percent = 0;
            if (saits_total_num != 0) {
                meeting_percent = 100 * meeting_num / saits_total_num;
            }
            meetingBo.setMeeting_percent(meeting_percent);
        }
        request.setAttribute("meetingBoList", meetingBoList);
        this.queryChildNum(request, groupBo, saitsUserBoList, friendsUserBoList, newSaitsUserBoList, meetingBoList);
    }

    /**
     * 下一级
     * @param request
     * @param groupBo
     */
    private void queryChildNum(HttpServletRequest request,
                               GroupBo groupBo,
                               List<UserBo> saitsUserBoList,
                               List<UserBo> friendsUserBoList,
                               List<UserBo> newSaitsUserBoList,
                               List<MeetingBo> meetingBos) {
        List<GroupBo> groupChildBos = groupService.getOneLevelChildGroupBoByGroupId(groupBo.getGroup_id());
        List<ChildGroupNumBo> childGroupNumBoList = new ArrayList<>();
        if (groupChildBos != null) {
            for (GroupBo groupBo1 : groupChildBos) {
                String code = groupBo1.getCode();
                ChildGroupNumBo indexNumBo = new ChildGroupNumBo();
                indexNumBo.setGroup_name(groupBo1.getName());
                indexNumBo.setGroup_id(groupBo1.getGroup_id());
                indexNumBo.setGroup_code(groupBo1.getCode());
                for (UserBo userBo : saitsUserBoList) {
                    if (userBo.getGroup_code().contains(code)) {
                        indexNumBo.setSaits_total_num(indexNumBo.getSaits_total_num()+1);
                    }
                }
                for (UserBo userBo : friendsUserBoList) {
                    if (userBo.getGroup_code().contains(code)) {
                        indexNumBo.setFriends_num(indexNumBo.getFriends_num()+1);
                    }
                }
                for (UserBo userBo : newSaitsUserBoList) {
                    if (userBo.getGroup_code().contains(code)) {
                        indexNumBo.setNew_saits_total_num(indexNumBo.getNew_saits_total_num()+1);
                    }
                }
                List<MeetingBo> chindMeetingBos=new ArrayList<>();
                for(MeetingBo meetingBo:meetingBos){
                    List<UserBo> userBos=meetingBo.getUserBoList();
                    MeetingBo chindMeetingBo=new MeetingBo();
                    chindMeetingBo.setMeeting_id(meetingBo.getMeeting_id());
                    chindMeetingBo.setMeeting_name(meetingBo.getMeeting_name());
                    for(UserBo userBo : userBos){
                        if(userBo.getGroup_code().contains(code)){
                            chindMeetingBo.getUserBoList().add(userBo);
                        }
                    }
                    chindMeetingBos.add(chindMeetingBo);
                }
                for(MeetingBo meetingBo:chindMeetingBos){
                    List<UserBo> userList=meetingBo.getUserBoList();
                    int meeting_num = 0;
                    if (userList != null && !userList.isEmpty()) {
                        meeting_num = userList.size();
                    }
                    meetingBo.setMeeting_num(meeting_num);
                    meetingBo.setUserBoList(userList);
                    int meeting_percent = 0;
                    if (indexNumBo.getSaits_total_num() != 0) {
                        meeting_percent = 100 * meeting_num / indexNumBo.getSaits_total_num();
                    }
                    meetingBo.setMeeting_percent(meeting_percent);

                }
                indexNumBo.setMeetingBoList(chindMeetingBos);
                childGroupNumBoList.add(indexNumBo);
            }
            request.setAttribute("childGroupNumBoList",childGroupNumBoList);
        }
    }

    /**
     * 登录
     *
     * @param userName
     * @param userPwd
     * @param yzm
     * @param sessionTimeOut
     * @param rememberMe
     * @param modelMap
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("/login")
    public String login(String userName, String userPwd, String yzm,
                        String sessionTimeOut, String rememberMe, ModelMap modelMap,
                        HttpServletRequest request, HttpServletResponse response) {
        String msg;
        //登录名，密码，验证码
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPwd)) {
            if (sessionTimeOut != null && "1".equals(sessionTimeOut)) {
                request.setAttribute("sessionTimeOut", sessionTimeOut);
            }
            return "/login";
        }
        logger.info("----login-----userName:" + userName);
        //start: 检查登录用户是不是valid_flag and del_flag
        GroupBo groupBo = groupService.getGroupBoByCode(userName);
        if (groupBo == null) {
            msg = "用户名错误";
            modelMap.addAttribute("msg", msg);
            modelMap.addAttribute("userName", userName);
            return "/login";
        }
        //stop: 检查登录用户是不是valid_flag and del_flag
        if (!userPwd.equals(groupBo.getPassword())) {
            msg = "用户名或密码错误";
            modelMap.addAttribute("msg", msg);
            modelMap.addAttribute("userName", userName);
            return "/login";
        } else {
            HttpSession session = request.getSession();
            new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, groupBo);
            new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_NAME, groupBo.getName());
            new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_USER_NAME, groupBo.getName());
            request.setAttribute("loginName", groupBo.getName());
            return "redirect:"+REDIRECT_URL+"/index.do";
        }
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/loginOut")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(CachePara.CACHE_PARA_LOGIN_USER);
        if (obj != null) {
            session.removeAttribute(CachePara.CACHE_PARA_USER_NAME);
            session.setAttribute(CachePara.CACHE_PARA_LOGIN_USER, null);
            session.removeAttribute(CachePara.CACHE_PARA_LOGIN_USER);
            session.setAttribute(CachePara.CACHE_PARA_USER_NAME, null);
            session.removeAttribute(CachePara.CACHE_PARA_LOGIN_NAME);
            session.setAttribute(CachePara.CACHE_PARA_LOGIN_NAME, null);
            new CacheService().clearCache(CachePara.CACHE_PARA_LOGIN_USER);
            new CacheService().clearCache(CachePara.CACHE_PARA_USER_NAME);
            new CacheService().clearCache(CachePara.CACHE_PARA_LOGIN_NAME);
        }
        return "redirect:"+REDIRECT_URL+"/login.do";
    }




}
