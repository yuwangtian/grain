package com.grain.sysconfig.sys.bo;

import com.grain.base.bo.BaseBo;

import java.io.Serializable;

/**
 * 公司表
 *
 * @author wzy
 * @since 2014-11-21 09:47:05
 * <p/>
 * <p/>
 * edited by yuchen 2014-11-27
 * 抽象出基类
 */

public class ArchCompany extends BaseBo implements Serializable {
    /**
     * 公司id
     */
    private Integer company_id;
    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司英文名称
     */
    private String en_name;
    /**
     * 公司类型：
     * 总部             1
     * 分公司          2
     * 合作公司      3
     * 合资公司      4
     */
    private Integer type;

    /* 父节点id*/
    private Integer parent_id;

    //	private Integer	sort_num;
//	
//	
//	public Integer getSort_num() {
//		return sort_num;
//	}
//	public void setSort_num(Integer sort_num) {
//		this.sort_num = sort_num;
//	}
    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

}
