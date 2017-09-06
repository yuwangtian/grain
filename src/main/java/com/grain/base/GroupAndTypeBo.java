package com.grain.base;

import com.grain.sysconfig.sys.bo.GroupBo;

/**
 * Created by yuchen
 * on 2017/9/6 0006.
 */
public class GroupAndTypeBo {
    GroupBo groupBo=null;
    String type=null;

    public GroupBo getGroupBo() {
        return groupBo;
    }

    public void setGroupBo(GroupBo groupBo) {
        this.groupBo = groupBo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
