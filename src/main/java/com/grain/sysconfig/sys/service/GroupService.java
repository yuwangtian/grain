package com.grain.sysconfig.sys.service;

import com.grain.sysconfig.sys.bo.GroupBo;
import com.grain.sysconfig.sys.dao.GroupDao;
import com.grain.utils.cache.CachePara;
import com.grain.utils.cache.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    /**
     * 通过部门编号找部门
     * @param code
     * @return
     */
    public GroupBo getGroupBoByCode(String code){
        return groupDao.getGroupBoByCode(code);
    }
    /**
     * 通过部门编号找部门
     * @param groupId
     * @return
     */
    public GroupBo getGroupBoByGroupId(String groupId){
        return groupDao.getGroupBoByGroupId(groupId);
    }

    /**
     * 通过部门编号找父部门
     * @param groupId
     * @return
     */
    public GroupBo getParentGroupBoByGroupId(String groupId){
        return groupDao.getParentGroupBoByGroupId(groupId);
    }

    /**
     * 通过部门找下一级子部门
     * @param groupId
     * @return
     */
    public List<GroupBo> getOneLevelChildGroupBoByGroupId(String groupId){
        return groupDao.getOneLevelChildGroupBoByGroupId(groupId);
    }
    /**
     * 通过部门找所有子集部门（递归）
     * @param groupId
     * @return
     */
    public List<GroupBo> getChildsGroupBoByGroupId(String groupId){
        return groupDao.getChildsGroupBoByGroupId(groupId);
    }
    /**
     * 找所有子集部门（递归）
     * @return
     */
    public List<GroupBo> getAllSmallGroups(HttpServletRequest request){
        GroupBo session = (GroupBo) new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_LOGIN_USER, null);
        String groupCode= session.getCode();
        if(StringUtils.isNotEmpty(groupCode)){
            groupCode=groupCode.substring(0,4);
        }
        if(groupCode==null){
            return null;
        }
        List<GroupBo> groupBos_new=new ArrayList<>();
        List<GroupBo> groupBos=  groupDao.getAllSmallGroups( groupCode);
        for(GroupBo groupBo:groupBos){
            if(groupBo.getCode().contains(groupCode)){
                groupBos_new.add(groupBo);
            }
        }
        return groupBos_new;
    }

}
