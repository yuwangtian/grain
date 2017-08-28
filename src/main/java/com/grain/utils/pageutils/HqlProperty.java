/**
 *
 */
package com.grain.utils.pageutils;

/**
 * @author Administrator
 */
public class HqlProperty {
    /**
     * @return the max
     */
    public Object getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Object max) {
        this.max = max;
    }

    /**
     * @return the min
     */
    public Object getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(Object min) {
        this.min = min;
    }

    /**
     * @return the dtoname
     */
    public String getDtoname() {
        return dtoname;
    }

    /**
     * @param dtoname the dtoname to set
     */
    public void setDtoname(String dtoname) {
        this.dtoname = dtoname;
    }

    /**
     * @return the queryType
     */
    public int getQueryType() {
        return queryType;
    }

    /**
     * @param queryType the queryType to set
     */
    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    /**
     * @return the isHql
     */
    public boolean isHql() {
        return isHql;
    }

    /**
     * @param isHql the isHql to set
     */
    public void setHql(boolean isHql) {
        this.isHql = isHql;
    }

    private Object max;
    private Object min;
    private String dtoname;
    private int queryType;
    private boolean isHql;
    private String dateFmt;
    private String tablealias;
    private static final int TYPE_EQ = 1;
    private static final int TYPE_NOT_EQ = 9;
    private static final int TYPE_EQ_NULL = 2;
    private static final int TYPE_EQ_NOTNULL = 3;
    private static final int TYPE_LIKE = 4;
    private static final int TYPE_EQ_TIME = 5;
    private static final int TYPE_COMPARE = 6;
    private static final int TYPE_COMPARE_EQ = 7;

    private static final int TYPE_COMPARE_TRUE = 8;
    private static final int TYPE_COLLECTION_IN = 10;
    private static final int TYPE_COLLECTION_NOTIN = 11;

    public static HqlProperty getEq(String name, Object value) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ, true);
    }

    public static HqlProperty getEq(String name, Object value, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ, talias);
    }

    public static HqlProperty getNotEq(String name, Object value) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_NOT_EQ, true);
    }

    public static HqlProperty getNotEq(String name, Object value, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_NOT_EQ, talias);
    }


    public static HqlProperty getEqNull(String name, Object value) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ_NULL, true);
    }

    public static HqlProperty getEqNull(String name, Object value, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ_NULL, talias);
    }

    public static HqlProperty getEqNotNull(String name, Object value) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ_NOTNULL, true);
    }

    public static HqlProperty getEqNotNull(String name, Object value, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ_NOTNULL, talias);
    }

    public static HqlProperty getLike(String name, Object value) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_LIKE, true);
    }

    public static HqlProperty getLike(String name, Object value, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_LIKE, talias);
    }


    public static HqlProperty getEQTime(String name, Object value, String dataFmt, String frmstr) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ_TIME, true, frmstr);
    }

    public static HqlProperty getEQTime(String name, Object value, String dataFmt, String frmstr, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_EQ_TIME, true, frmstr, talias);
    }

    public static HqlProperty getCompare(String name, Object max, Object value) {
        return new HqlProperty(name, max, value, HqlProperty.TYPE_COMPARE, true);
    }

    public static HqlProperty getCompare(String name, Object max, Object value, String talias) {
        return new HqlProperty(name, max, value, HqlProperty.TYPE_COMPARE, talias);
    }

    public static HqlProperty getCompareEQ(String name, Object max, Object value) {
        return new HqlProperty(name, max, value, HqlProperty.TYPE_COMPARE_EQ, true);
    }

    public static HqlProperty getCompareEQ(String name, Object max, Object value, String talias) {
        return new HqlProperty(name, max, value, HqlProperty.TYPE_COMPARE_EQ, talias);
    }

    public static HqlProperty getCompareTRUE(String name, Object value) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_COMPARE_TRUE, true);
    }

    public static HqlProperty getCompareTRUE(String name, Object value, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_COMPARE_TRUE, talias);
    }

    public static HqlProperty getCollectionIn(String name, Object value) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_COLLECTION_IN, true);
    }

    public static HqlProperty getCollectionIn(String name, Object value, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_COLLECTION_IN, talias);
    }

    public static HqlProperty getCollectionNotIn(String name, Object value) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_COLLECTION_NOTIN, true);
    }

    public static HqlProperty getCollectionNotIn(String name, Object value, String talias) {
        return new HqlProperty(name, null, value, HqlProperty.TYPE_COLLECTION_NOTIN, talias);
    }

    /**
     * @return the dateFmt
     */
    public String getDateFmt() {
        return dateFmt;
    }

    /**
     * @param dateFmt the dateFmt to set
     */
    public void setDateFmt(String dateFmt) {
        this.dateFmt = dateFmt;
    }

    public HqlProperty(String dtoname, Object max, Object min,
                       int chkType, boolean isHql) {
        this.dtoname = dtoname;
        this.max = max;
        this.min = min;
        this.queryType = chkType;
        this.isHql = isHql;
    }

    public HqlProperty(String dtoname, Object max, Object min,
                       int chkType, String talias) {
        this.dtoname = dtoname;
        this.max = max;
        this.min = min;
        this.queryType = chkType;
        this.isHql = isHql;
        this.tablealias = talias;
    }

    /*
     *
     */
    public HqlProperty(String dtoname, Object max, Object min,
                       int chkType, boolean isHql, String frmstr) {
        this.dtoname = dtoname;
        this.max = max;
        this.min = min;
        this.queryType = chkType;
        this.isHql = isHql;
        this.dateFmt = frmstr;
    }

    /**
     * @param dtoname
     * @param max
     * @param min
     * @param chkType
     * @param isHql
     * @param frmstr
     */
    public HqlProperty(String dtoname, Object max, Object min,
                       int chkType, boolean isHql, String frmstr, String talias) {
        this.dtoname = dtoname;
        this.max = max;
        this.min = min;
        this.queryType = chkType;
        this.isHql = isHql;
        this.dateFmt = frmstr;
        this.tablealias = talias;
    }

    /**
     * @return the tablealias
     */
    public String getTablealias() {
        return tablealias;
    }

    /**
     * @param tablealias the tablealias to set
     */
    public void setTablealias(String tablealias) {
        this.tablealias = tablealias;
    }

}
