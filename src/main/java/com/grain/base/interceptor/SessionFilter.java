package com.grain.base.interceptor;


import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.utils.PropertiesUtil;
import com.grain.utils.cache.CachePara;
import com.grain.base.bo.base.Pmodule;
import com.grain.utils.cache.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SessionFilter extends OncePerRequestFilter {
    private static final String DEFAULT_RETURN_PATH = "login.do";
    private static final String seperator = "/";
    // 不过滤的uri
    private static String[] notFilter = new String[]{"/login.do", "checkAndsendEmail.do", "checkToken.do", "/loginOut.do", "/getOrderExpiredUser.do", "/findPassWord.do", "/user/reset_password.do", "/changePassword.do", "/orderOperateAction/getOrderForKc.do"};
    private static String[] loginNoFilter = new String[]{"/index.jsp", "/todo.jsp", "/changePasswordForm.do", "/changePassword.do", "/changeUserRoles.do", "/homeAction/getHomePage.do", "/homeAction/checkUrlAvailable.do", "/ueditor_utf8_jsp/jsp/controller.jsp", "/NoticeAction/getFile.do", "/UserMsgAction/getUserNoticeMsg.do"};
    private static String RETURN_PATH;
    private Logger logger = Logger.getLogger(this.getClass());

    protected static Properties properties = PropertiesUtil
            .loadProperties("/config.properties");

    protected static String REDIRECT_URL = properties
            .getProperty("redirect_url");
    @Override
    public void initFilterBean() throws ServletException {
        FilterConfig filterConfig = getFilterConfig();
        RETURN_PATH = filterConfig.getInitParameter("RETURN_PATH");
        if (StringUtils.isBlank(RETURN_PATH)) {
            RETURN_PATH = DEFAULT_RETURN_PATH;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("doing session filter.");
        // 请求的uri
        String servletPath = request.getServletPath();
        // 请求菜单的id
        String pageId = request.getParameter("pageId");
        Map<String, String[]> parms = request.getParameterMap();
        logger.debug("请求所属的菜单id：" + pageId);
        logger.debug("请求路径：" + servletPath);
        StringBuffer bf = new StringBuffer();
        for (String key : parms.keySet()) {
            bf.append("参数:" + key + "值:" + request.getParameter(key) + "   ");
        }
        logger.debug(bf);
        // 是否过滤
        boolean doFilter = true;
        for (String s : notFilter) {
            if (servletPath.contains(s)) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        // 从session中获取登录者实体

        ////System.out.println("-----------1-------------------------SessionFilter:"+loginUser);
        //GroupBo loginUser = (GroupBo) request.getSession().getAttribute(CachePara.CACHE_PARA_LOGIN_USER);
        GroupBo   loginUser = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);

        // //System.out.println("-----------2-------------------------SessionFilter:"+loginUser);
        //登录后不进行过滤的页面
        if (loginUser != null) {
            for (String s : loginNoFilter) {
                if (s.equals(servletPath)) {
                    doFilter = false;
                    break;
                }
            }
        }
        if (doFilter) {
            // 执行权限过滤
            if (loginUser == null) {
                // 如果session中不存在登录者实体，则弹出框提示重新登录
                response.sendRedirect(REDIRECT_URL+"/login.do?sessionTimeOut=1");
            } else {

                    filterChain.doFilter(request, response);
                    return;
                }

        } else {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
            return;
        }
        logger.debug("doing session filter finished.");
    }

    @Override
    public void destroy() {

    }
}