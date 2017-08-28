package com.grain.base.interceptor;

import com.alibaba.fastjson.JSON;
import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.utils.SpringContextUtil;

import com.grain.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * @author anqi
 * @since 2014/8/18.
 */
public class CookieFilter extends OncePerRequestFilter {
    private static final String DEFAULT_RETURN_PATH = "login.do";
    private static final String seperator = "/";
    private static String RETURN_PATH;
    private static Properties CONFIG;
    private static String PRIVILEGE_URL;
    private static String LOGIN_URL;
    private static String LOGIN_PARAMS;

    @Override
    public void initFilterBean() throws ServletException {
        FilterConfig filterConfig = getFilterConfig();
        RETURN_PATH = filterConfig.getInitParameter("RETURN_PATH");
        if (StringUtils.isBlank(RETURN_PATH)) {
            RETURN_PATH = DEFAULT_RETURN_PATH;
        }
        CONFIG = (Properties) SpringContextUtil.getBean("config");
        PRIVILEGE_URL = CONFIG.getProperty("privilege.url");
        LOGIN_URL = PRIVILEGE_URL + CONFIG.getProperty("privilege.login.url");
        LOGIN_PARAMS = CONFIG.getProperty("privilege.login.params");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("doing cookie filter.");
        GroupBo loginUser = (GroupBo) request.getSession().getAttribute("loginUser");
        String servletPath = request.getServletPath();
        logger.debug("servlet path:" + servletPath);
        if (loginUser != null) {
            filterChain.doFilter(request, response);
        } else if (servletPath.equals(seperator + RETURN_PATH)) {
            filterChain.doFilter(request, response);
        } else {
            String cookie_userName = null;
            String cookie_userPwd = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("uc2".equals(cookie.getName())) {
                        cookie_userName = cookie.getValue();
                    } else if ("uc2".equals(cookie.getName())) {
                        cookie_userPwd = cookie.getValue();
                    }
                }
            }
            if (StringUtils.isNotBlank(cookie_userName)) {
                String loginParams = String.format(LOGIN_PARAMS, cookie_userName, cookie_userPwd);
                String res = HttpClientUtil.get(LOGIN_URL + "?" + loginParams);
                if (res == null || "error".equals(res)) {
                    response.sendRedirect(request.getContextPath() + seperator + RETURN_PATH);
                } else {
                    try {
                        loginUser = JSON.parseObject(res, GroupBo.class);
                    } catch (Exception e) {
                        logger.error("json转为对象[" + GroupBo.class + "]失败，json text:" + res);
                        throw e;
                    }
                    request.getSession().setAttribute("loginUser", loginUser);
                    request.getSession().setAttribute("userName", res);
                    response.sendRedirect(request.getContextPath());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
        logger.debug("doing cookie filter finished.");
    }

    @Override
    public void destroy() {

    }
}
