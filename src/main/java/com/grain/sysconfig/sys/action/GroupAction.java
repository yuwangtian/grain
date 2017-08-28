package com.grain.sysconfig.sys.action;

import com.grain.sysconfig.sys.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wzy
 * @since 2014-11-22 11:24:32
 */

@Controller
public class GroupAction {
    /**
     * slf4j 日志
     */
    private static final Logger logger = LoggerFactory
            .getLogger(GroupAction.class);


    protected static String jsonData = "";

    @Autowired
    private GroupService service;


    /**
     * lifeng
     * 根据公司ID获取部门列表列表
     *
     * @return
     */
    @RequestMapping("group/getGroupList")
    @ResponseBody
    public String getGroupList(HttpServletRequest request) {
        String company_id = request.getParameter("company_id");
        String filterLevelList = request.getParameter("filterLevelList");
        logger.debug("正在加载部门数据，公司ID ：" + company_id);
        //List<GroupBo> list = service.getDaGroupListByCompanyId(company_id, filterLevelList);
       // jsonData = JSON.toJSONString(list);
        return jsonData;
    }


}
