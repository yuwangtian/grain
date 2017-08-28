package com.grain.base.bo;

import com.grain.sysconfig.sys.bo.ArchDictionary;

import java.io.Serializable;
import java.util.List;

/**
 * JSON的对象
 *
 * @author yuchen
 * @date 2015-1-14
 */
public class JsonObject implements Serializable {

    private List<ArchDictionary> list;

    public List<ArchDictionary> getList() {
        return list;
    }

    public void setList(List<ArchDictionary> list) {
        this.list = list;
    }


}
