package com.grain.base.log.bo;

import com.grain.base.bo.BaseBo;

import java.util.Date;


/**
 * 系统操作日志表
 *
 * @author Administrator
 *         table : base_operate_log
 */
public class OperateLogBo extends BaseBo {

    private Integer id;
    private Integer page_id; //菜单ID
    private Integer operate_user_id; //操作人ID
    private Date operate_time;   //操作时间
    private String order_id;//操作订单号
    private String operate_content;  //操作内容
    private Integer operate_type; //操作类型 1：查看 2：增加 3：修改 4：删除
    private String operate_ip;  //操作人（客户端）的IP地址 例如：192.168.60.221
    private String sys_url_port; //	服务器的URL和端口号 例如：http://192.168.60.91:8080/workorder


    public static final Integer operate_type_seach = 1;
    public static final Integer operate_type_add = 2;
    public static final Integer operate_type_update = 3;
    public static final Integer operate_type_del = 4;


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage_id() {
        return page_id;
    }

    public void setPage_id(Integer page_id) {
        this.page_id = page_id;
    }

    public Integer getOperate_user_id() {
        return operate_user_id;
    }

    public void setOperate_user_id(Integer operate_user_id) {
        this.operate_user_id = operate_user_id;
    }

    public Date getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(Date operate_time) {
        this.operate_time = operate_time;
    }

    public String getOperate_content() {
        return operate_content;
    }

    public void setOperate_content(String operate_content) {
        this.operate_content = operate_content;
    }

    public Integer getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(Integer operate_type) {
        this.operate_type = operate_type;
    }

    public String getOperate_ip() {
        return operate_ip;
    }

    public void setOperate_ip(String operate_ip) {
        this.operate_ip = operate_ip;
    }

    public String getSys_url_port() {
        return sys_url_port;
    }

    public void setSys_url_port(String sys_url_port) {
        this.sys_url_port = sys_url_port;
    }


}
