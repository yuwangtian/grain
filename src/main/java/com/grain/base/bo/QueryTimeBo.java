package com.grain.base.bo;

import java.util.Date;

/**
 * 查询时间
 * Created by yuchen
 * on 11/15/2017.
 */
public class QueryTimeBo {
    Date beginDate;
    Date endDate;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
