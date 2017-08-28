package com.grain.base.web;

import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.utils.cache.CachePara;
import com.grain.base.bo.base.Pmodule;
import com.grain.utils.cache.CacheService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MenuController {
    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("/getMenu")

    private String getMenu(HttpServletRequest request, ModelMap modelMap) {
        List<Pmodule> menuList = null;
        List<Pmodule> moduleList = null;
        GroupBo loginUser = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        if (loginUser != null) {

        }

        modelMap.put("menuList", menuList);
        return "../../menu";
    }

    public List<Pmodule> getList(List<Pmodule> moduleList, Integer pid) {
        List<Pmodule> menuList = new ArrayList<>();
        for (Pmodule pmodule : moduleList) {
            if (pmodule.getParent_id() != null && pmodule.getParent_id().equals(pid)) {
                menuList.add(pmodule);
            }
        }
        return menuList;
    }

    @RequestMapping("/gotoMenu")
    public String gotoMenu(HttpServletRequest request, ModelMap modelMap) {
        List<Pmodule> menuList = null;
        List<Pmodule> moduleList = null;
        GroupBo loginUser = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        List<HashMap<String, String>> firstMenu = new ArrayList<HashMap<String, String>>();
        HashMap<String, List<HashMap<String, String>>> leftMenuMap = new HashMap<String, List<HashMap<String, String>>>();
        if (loginUser != null) {



        }
        modelMap.put("firstMenu", firstMenu);
        modelMap.put("leftMenuMap", leftMenuMap);
        modelMap.put("menuList", menuList);
        return "../../index";
    }


}
