package com.grain.base.interceptor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.utils.SpringContextUtil;
import com.grain.utils.cache.CachePara;
import org.springframework.util.StringUtils;

import com.grain.base.log.bo.LoginLogBo;
import com.grain.base.log.imp.LoginLogService;
import com.grain.utils.DateUtils;
import com.grain.utils.cache.CacheService;

/**
 * 登录日志监听器
 *
 * @author Administrator
 */
public class LoginLogListener implements HttpSessionListener, ServletRequestListener {


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
