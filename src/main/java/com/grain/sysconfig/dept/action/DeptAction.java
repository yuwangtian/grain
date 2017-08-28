package com.grain.sysconfig.dept.action;

import com.grain.sysconfig.sys.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 组织架构 action
 *
 * @author wqm
 */

@Controller
@RequestMapping("DeptAction")
public class DeptAction  {

    @Autowired
    private GroupService groupServie;

    /**
     * slf4j 日志
     */
    private static final Logger logger = LoggerFactory
            .getLogger(DeptAction.class);

    private static String jsonData = "";

    @RequestMapping("gotoDept")
    public String gotoDept(HttpServletRequest request) {
        logger.debug("正准备跳转到组织机构的功能页面....");
        return "/dept/deptInfo";
    }
} 

			
			
			
		
