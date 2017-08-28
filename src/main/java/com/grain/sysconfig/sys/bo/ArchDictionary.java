package com.grain.sysconfig.sys.bo;

import com.alibaba.fastjson.JSON;
import com.grain.base.bo.BaseBo;

import java.io.Serializable;

/**
 * 字典表
 *
 * @author wzy
 * @serial 2014-11-21 10:29:57
 */
public class ArchDictionary extends BaseBo implements Serializable {

    private Integer dic_id; //数据字典id
    private Integer dic_type_id;//字典类型Id
    private String ch_name;//字典名称
    private String en_name;//字典名称
    private String code_value;//字典id
    private String code_name;//字典id

    private String defaultVals; //默认显示值

    public ArchDictionary() {
        super();
    }

    public ArchDictionary(String en_name) {
        this.en_name = en_name;
    }

    public Integer getDic_id() {
        return dic_id;
    }

    public void setDic_id(Integer dic_id) {
        this.dic_id = dic_id;
    }

    public Integer getDic_type_id() {
        return dic_type_id;
    }

    public void setDic_type_id(Integer dic_type_id) {
        this.dic_type_id = dic_type_id;
    }

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getCode_value() {
        return code_value;
    }

    public void setCode_value(String code_value) {
        this.code_value = code_value;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getDefaultVals() {
        return defaultVals;
    }

    public void setDefaultVals(String defaultVals) {
        this.defaultVals = defaultVals;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }


}
