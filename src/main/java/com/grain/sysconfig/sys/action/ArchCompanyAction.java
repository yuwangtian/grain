package com.grain.sysconfig.sys.action;

import com.grain.sysconfig.sys.bo.ArchCompany;
import com.grain.sysconfig.sys.service.ArchCompanyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzy
 * @since 2014-11-22 11:24:32
 */

@Controller
public class ArchCompanyAction {
    /**
     * slf4j 日志
     */
    private static final Logger logger = LoggerFactory
            .getLogger(ArchCompanyAction.class);


    protected static String jsonData = "";

    @Autowired
    private ArchCompanyServiceImpl service;

    @RequestMapping("/companyList")
    public String getList(ModelMap map, ArchCompany model) {
        List<ArchCompany> list = service.getList(model);
        for (ArchCompany archCompany : list) {
            logger.debug(archCompany.getName() + "  " + archCompany.getCompany_id());
        }
        map.put("list", list);
        return "sys/companyList";
    }

    /**
     * lifeng
     * 获取公司列表
     *
     * @param model
     * @return
     */
    @RequestMapping("company/getCompanyList")
    @ResponseBody
    public List<ArchCompany> getCompanyList(ArchCompany model) {
        List<ArchCompany> listAll = service.getList(model);
        List<ArchCompany> list = new ArrayList<ArchCompany>();
        for (ArchCompany com : listAll) {
            if (com.getType() != 0) {
                list.add(com);
            }
        }
        return list;
    }


}
