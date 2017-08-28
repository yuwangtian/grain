/**
 *
 */
package com.grain.utils.pageutils;

import com.grain.base.bo.BaseBo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator
 */
public class LimitInfo extends BaseBo {
    private int recordCount;
    private int pageCount;
    private int pageSize;
    private int startResult;
    private int pageNo = 1;
    private String action;
    private String tableAlias = "Z";


    public static final String ASC = "asc";
    public static final String DESC = "desc";
    private Map orderProperty = new HashMap();
    private Map filterMap = new HashMap();
    public String filtersql;
    public String ordersql;


    public int getStartResult() {
        return (this.getPageNo() - 1) * this.getPageSize();
    }

    public void setStartResult(int startResult) {
        this.startResult = startResult;
    }

    public int getFirstResult() {
        return (this.getPageNo() - 1) * this.getPageSize();
    }

    /**
     *
     */
    public LimitInfo() {
        this.pageSize = 10000;
    }

	
	


	/* (non-Javadoc)
	 * @see com.wansin.common.util.limit.LimitInfoI#getSql()
	
	public Object[] getSql() {
		Object[] obj=new Object[2];
		Map paramsMap=new HashMap();
		StringBuffer sb=new StringBuffer();
		if(filterMap.size()==0)
		{
			obj[0]="";
		}
		
		Iterator it=filterMap.keySet().iterator();
		while(it.hasNext())
		{
			String key=(String)it.next();
			HqlProperty hp=(HqlProperty)filterMap.get(key);
			
			Object max=hp.getMax();
			Object min=hp.getMin();
			String relatedto=hp.getTablealias();
			switch(hp.getQueryType())
			{
				case 1:
					if(min!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and " +relatedto+"."+key+"=:"+key);
						}else
						{
							sb.append(" and " +this.tableAlias+"."+key+"=:"+key);
						}
						paramsMap.put(key, min);
					}
					break;
				case 2:
					if(relatedto!=null && !"".equals(relatedto))
					{
						sb.append(" and "+relatedto+"."+key+" is null ");
					}else
					{
						sb.append(" and "+this.tableAlias+"."+key+" is null ");
					}
					break;
				case 3:
					if(relatedto!=null && !"".equals(relatedto))
					{
						sb.append(" and "+relatedto+"."+key+" is not null ");
					}else
					{
						sb.append(" and "+this.tableAlias+"."+key+" is not null ");
					}
					break;
				case 4:
					if(min!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and "+relatedto+"."+key+"  like :"+key+" ");
						}else
						{
							sb.append(" and "+this.tableAlias+"."+key+"  like :"+key+" ");
						}
					}
					paramsMap.put(key, min);
					break;
				case 5:
					if(min!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and to_char("+relatedto+"."+key+",'"+hp.getDateFmt()+"')=:"+key);
						}else
						{
							sb.append(" and to_char("+this.tableAlias+"."+key+",'"+hp.getDateFmt()+"')=:"+key);
						}
					}
					paramsMap.put(key, min);
					break;
				case 6:
					if(min==null && max==null)break;
					if(min!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and "+relatedto+"."+key+">:min"+key);
						}else
						{
							sb.append(" and "+this.tableAlias+"."+key+">:min"+key);
						}
						paramsMap.put("min"+key, min);
					}
					if(max!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and "+relatedto+"."+key+"<:max"+key);
						}else{
							sb.append(" and "+this.tableAlias+"."+key+"<:max"+key);
						}
						paramsMap.put("max"+key, max);
					}
					break;
				case 7:
					if(min==null && max==null)break;
					if(min!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and "+relatedto+"."+key+">=:min"+key);
						}else
						{
							sb.append(" and "+this.tableAlias+"."+key+">=:min"+key);
						}

						paramsMap.put("min"+key, min);
					}
					if(max!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and "+relatedto+"."+key+"<=:max"+key);
						}else
						{
							sb.append(" and "+this.tableAlias+"."+key+"<=:max"+key);
						}
						paramsMap.put("max"+key, max);
					}
					break;
				case 8:
					if((Boolean)min)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and " +relatedto+"."+key+" is true ");
						}else
						{
							sb.append(" and " +this.tableAlias+"."+key+" is true ");
						}
					}else
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and " +relatedto+"."+key+" is false ");
						}else
						{
							sb.append(" and " +this.tableAlias+"."+key+" is false ");
						}
					}
					break;
				case 9:
					if(min!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and " +relatedto+"."+key+"!=:"+key);
						}else
						{
							sb.append(" and " +this.tableAlias+"."+key+"!=:"+key);
						}
							paramsMap.put(key, min);
					}
					break;
				case 10:
					if(min!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and " +relatedto+"."+key+" in (");
						}else
						{
							sb.append(" and " +this.tableAlias+"."+key+" in (");
						}
						String collectionstr[]=((String)min).split(",");
						for(int i=0;i<collectionstr.length;i++)
						{
							String paramname=key+"uncolarrstr"+i;
							if(i!=collectionstr.length-1)
								sb.append(":"+paramname+" ,");
							else
								sb.append(":"+paramname);
							paramsMap.put(paramname, collectionstr[i]);
						}
						sb.append(")");
					}
					break;
				case 11:
					if(min!=null)
					{
						if(relatedto!=null && !"".equals(relatedto))
						{
							sb.append(" and " +relatedto+"."+key+" not in (");
						}else
						{
							sb.append(" and " +this.tableAlias+"."+key+" not in (");
						}
							String collectionstr[]=((String)min).split(",");
							for(int i=0;i<collectionstr.length;i++)
							{
								String paramname=key+"colarrstr"+i;
								if(i!=collectionstr.length-1)
									sb.append(":"+paramname+" ,");
								else
									sb.append(":"+paramname);
								paramsMap.put(paramname, collectionstr[i]);
							}
							sb.append(")");
					}
					break;
				default:break;
			}
		}
		
		obj[0]=sb.toString();
		obj[1]=paramsMap;
		return obj;
	}
	
	public String getOrdersSql()
	{
		String orderStr="";
		StringBuffer sb=new StringBuffer(" order by ");
		if(this.orderProperty.isEmpty())return "";
		Iterator it=this.orderProperty.keySet().iterator();
		while(it.hasNext())
		{
			String key=(String)it.next();
			String value=(String)this.orderProperty.get(key);
			sb.append( key+" "+value +" ,");
		}
		orderStr=sb.substring(0, sb.length()-1);
		return orderStr;
	}
	*/

    /**
     *
     */
    public LimitInfo(String tableId, int pageSize) {
        this.tableAlias = tableId;
        this.pageSize = pageSize;
        this.pageNo = 1;
    }

    public LimitInfo(int pageSize) {
        this.pageSize = pageSize;
        this.pageNo = 1;
    }

    /**
     * @param property
     * @param ordertype
     */
    public void addOrder(String property, String ordertype) {
        this.orderProperty.put(property, ordertype);
    }

    /**
     * @param hqlProperty
     */
    public void addFiler(HqlProperty hqlProperty) {
        this.filterMap.put(hqlProperty.getDtoname(), hqlProperty);
    }

    /**
     * @param request
     * @throws Exception
     */
    public LimitInfo(HttpServletRequest request) throws Exception {
        String pageNoStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("rows");
        if (pageNoStr == null) {
            this.pageNo = 1;
        } else {
            this.pageNo = Integer.parseInt(pageNoStr);
        }

        if (pageSizeStr == null) {
            this.pageSize = 10;
        } else {
            this.pageSize = Integer.parseInt(pageSizeStr);
        }
    }

    public void clearFilter() {
        this.filterMap.clear();
        //this.orderProperty.clear();
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        this.pageCount = this.getRecordCount() % this.getPageSize() == 0 ? this.getRecordCount() / this.getPageSize() : this.getRecordCount() / this.getPageSize() + 1;
        return pageCount;
    }

    /**
     * @return the recordCount
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * @param recordCount the recordCount to set
     */
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * @return the pageNum
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageNum to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the tableAlias
     */
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * @param tableAlias the tableAlias to set
     */
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    /**
     * @return the filterMap
     */
    public Map getFilterMap() {
        return filterMap;
    }

    /**
     * @param filterMap the filterMap to set
     */
    public void setFilterMap(Map filterMap) {
        this.filterMap = filterMap;
    }


    public String getFiltersql() {
        Object[] obj = new Object[2];
        Map paramsMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        if (filterMap.size() == 0) {
            obj[0] = "";
        }

        Iterator it = filterMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            HqlProperty hp = (HqlProperty) filterMap.get(key);

            Object max = hp.getMax();
            Object min = hp.getMin();
            String relatedto = hp.getTablealias();
            switch (hp.getQueryType()) {
                case 1:
                    if (min != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + " = '" + min + "'");
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + "= '" + min + "'");
                        }
                        paramsMap.put(key, min);
                    }
                    break;
                case 2:
                    if (relatedto != null && !"".equals(relatedto)) {
                        sb.append(" and " + relatedto + "." + key + " is null ");
                    } else {
                        sb.append(" and " + this.tableAlias + "." + key + " is null ");
                    }
                    break;
                case 3:
                    if (relatedto != null && !"".equals(relatedto)) {
                        sb.append(" and " + relatedto + "." + key + " is not null ");
                    } else {
                        sb.append(" and " + this.tableAlias + "." + key + " is not null ");
                    }
                    break;
                case 4:
                    if (min != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + "  like '" + min + "' ");
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + "  like '" + min + "' ");
                        }
                    }
                    paramsMap.put(key, min);
                    break;
                case 5:
                    if (min != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and to_char(" + relatedto + "." + key + ",'" + hp.getDateFmt() + "')=:" + key);
                        } else {
                            sb.append(" and to_char(" + this.tableAlias + "." + key + ",'" + hp.getDateFmt() + "')=:" + key);
                        }
                    }
                    paramsMap.put(key, min);
                    break;
                case 6:
                    if (min == null && max == null) break;
                    if (min != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + ">:min" + key);
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + ">:min" + key);
                        }
                        paramsMap.put("min" + key, min);
                    }
                    if (max != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + "<:max" + key);
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + "<:max" + key);
                        }
                        paramsMap.put("max" + key, max);
                    }
                    break;
                case 7:
                    if (min == null && max == null) break;
                    if (min != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + ">= '" + min + "'");
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + ">='" + min + "'");
                        }

                        paramsMap.put(key, min);
                    }
                    if (max != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + "<= '" + max + "'");
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + "<='" + max + "'");
                        }
                        paramsMap.put(key, max);
                    }
                    break;
                case 8:
                    if ((Boolean) min) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + " is true ");
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + " is true ");
                        }
                    } else {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + " is false ");
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + " is false ");
                        }
                    }
                    break;
                case 9:
                    if (min != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + "!=" + min);
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + "!=" + min);
                        }
                        paramsMap.put(key, min);
                    }
                    break;
                case 10:
                    if (min != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + " in (");
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + " in (");
                        }
                        String collectionstr[] = ((String) min).split(",");
                        for (int i = 0; i < collectionstr.length; i++) {
                            if (collectionstr[i].length() > 5) {

                                String paramname = collectionstr[i].toString();

                                //TP青岛更改 曹群星 2017-01-17 ADD START
                                if ("order_id".equals(key)) {
                                    paramname = "'" + paramname + "'";
                                }
                                //TP青岛更改 曹群星 2017-01-17 ADD END

                                if (i != collectionstr.length - 1)
                                    sb.append(paramname + ",");
                                else
                                    sb.append(paramname);
                                paramsMap.put(paramname, collectionstr[i]);

                            } else {
                                int paramname = Integer.parseInt(collectionstr[i]);
                                if (i != collectionstr.length - 1)
                                    sb.append(paramname + ",");
                                else
                                    sb.append(paramname);
                                paramsMap.put(paramname, collectionstr[i]);
                            }

                        }
                        sb.append(")");
                    }
                    break;
                case 11:
                    if (min != null) {
                        if (relatedto != null && !"".equals(relatedto)) {
                            sb.append(" and " + relatedto + "." + key + " not in (");
                        } else {
                            sb.append(" and " + this.tableAlias + "." + key + " not in (");
                        }
                        String collectionstr[] = ((String) min).split(",");
                        for (int i = 0; i < collectionstr.length; i++) {
                            int paramname = Integer.parseInt(collectionstr[i]);
                            if (i != collectionstr.length - 1)
                                sb.append(paramname + " ,");
                            else
                                sb.append(paramname);
                            paramsMap.put(paramname, collectionstr[i]);
                        }
                        sb.append(")");
                    }
                    break;
                default:
                    break;
            }
        }

        //obj[0]=sb.toString();
        obj[1] = paramsMap;
        return sb.toString();
    }

    public void setFiltersql(String filtersql) {
        this.filtersql = filtersql;
    }

    public String getOrdersql() {
        String orderStr = "";
        StringBuffer sb = new StringBuffer(" order by ");
        if (this.orderProperty.isEmpty()) return "";
        Iterator it = this.orderProperty.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = (String) this.orderProperty.get(key);
            sb.append(key + " " + value + " ,");
        }
        orderStr = sb.substring(0, sb.length() - 1);
        //System.out.println(orderStr);
        return orderStr;

    }

    public void setOrdersql(String ordersql) {
        this.ordersql = ordersql;
    }


}
