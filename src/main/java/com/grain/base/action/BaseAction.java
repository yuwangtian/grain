package com.grain.base.action;

import com.alibaba.fastjson.JSON;
import com.grain.base.log.bo.OperateLogBo;
import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.utils.PropertiesUtil;
import com.grain.utils.cache.CachePara;
import com.grain.utils.pageutils.Msg;
import com.grain.base.bo.base.Pmodule;
import com.grain.base.log.AppLogService;
import com.grain.sysconfig.role.bo.RoleBo;
import com.grain.utils.cache.CacheService;
import com.grain.utils.pageutils.LimitInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author yuchen 2014-11-24
 *         <p/>
 *         流程基础父类
 *         分页参数封装
 *         sql语句参数封装
 *         保存操作日志方法
 */
@Controller
public class BaseAction<T> {

    protected LimitInfo jQueryLimit;
    protected static String jsonData = "";

    @Autowired
    private AppLogService appLogService;

    protected static List<Pmodule> mList;
    protected static String pageId;
    /**
     * slf4j 日志
     */
    private static final Logger logger = LoggerFactory
            .getLogger(BaseAction.class);
    protected static Properties properties = PropertiesUtil
            .loadProperties("/config.properties");

    protected static String REDIRECT_URL = properties
            .getProperty("redirect_url");

    /**
     * 获取登录人对象
     *
     * @param request
     * @return
     */
    protected GroupBo getLoginUserFromSession(HttpServletRequest request) {
        GroupBo loginUser = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        return loginUser;
    }


    /**
     * 获取登录人对象的真实姓名
     *
     * @param request
     * @return
     */
    protected String getNameFromSession(HttpServletRequest request) {
        GroupBo loginUser = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        String loginUserName = null;
        if (loginUser != null) {
            loginUserName = loginUser.getName();
        }
        logger.debug("loginUserName=" + loginUserName);
        return loginUserName;
    }


    /**
     * 获取登录人对象的登录名
     *
     * @param request
     * @return
     */
    protected String getLoginUserNameFromSession(HttpServletRequest request) {
        GroupBo loginUser = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        String loginUserName = null;
        if (loginUser != null) {
            loginUserName = loginUser.getCode();
        }
        logger.debug("loginUserName=" + loginUserName);
        return loginUserName;
    }









    public String getIpAddr(HttpServletRequest request) {

        String ipAddress = null;
        //ipAddress = this.getRequest().getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;

    }

    /**
     * 重载函数，暂时不让之前调用该函数的地方报错
     *
     * @param request
     */
    public void insertAppLog(HttpServletRequest request) {

    }

    /**
     * 返回成功信息
     *
     * @return msg
     */
    protected Msg success() {
        return new Msg(true, "操作成功");
    }

    /**
     * 返回成功信息
     *
     * @param data 传递给前端的数据
     * @return msg
     */
    protected Msg success(Object data) {
        return new Msg(true, data);
    }

    /**
     * 返回错误提示信息
     *
     * @param data 传递给前端的数据
     * @return msg
     */
    protected Msg fail(Object data) {

        return new Msg(false, data);
    }

    /**
     * 获取通过过滤器请求的权限URL 并绑定操作权限数据
     */
    public void sendRequestForRole(HttpServletRequest request) {
        mList = (List<Pmodule>) request.getAttribute("mList");
        pageId = (String) request.getAttribute("pageId");
        request.setAttribute("pageId", pageId);
        request.setAttribute("mList", JSON.toJSONString(mList));
    }




    /**
     * 出使化logbo
     *
     * @param loginUser
     * @param orderId
     * @param content
     * @param operateType
     * @return
     */
    protected OperateLogBo initOperateLogBo(GroupBo loginUser, String orderId, String content, Integer operateType) {
        OperateLogBo logBo = new OperateLogBo();
        logBo.setOperate_type(operateType);//类型
        logBo.setOrder_id(orderId);//操作订单号
        logBo.setOperate_content(content);//内容
        return logBo;
    }
}
