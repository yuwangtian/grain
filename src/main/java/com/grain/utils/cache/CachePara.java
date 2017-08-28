package com.grain.utils.cache;

/**
 * 缓存常量
 *
 * @author yuchen
 * @date 2015-1-14
 */
public class CachePara {
    /**
     * 菜单缓存
     */
    public static String CACHE_PROJECT_ID = "grain_";
    /**
     * 菜单缓存
     */
    public static String CACHE_PARA_MENU = CACHE_PROJECT_ID + "allMenuListJson";
    /**
     * 数据字典缓存
     */
    public static String CACHE_PARA_DIC = CACHE_PROJECT_ID + "dicJson";
    /**
     * 验证码
     */
    public static String CACHE_PARA_VALIDATE_CODE = CACHE_PROJECT_ID + "code";
    /**
     * 登录的user对象
     */
    public static String CACHE_PARA_LOGIN_USER = CACHE_PROJECT_ID + "loginUser";
    /**
     * 登录人姓名
     */
    public static String CACHE_PARA_USER_NAME = CACHE_PROJECT_ID + "userName";
    /**
     * 登录人小组
     */
    public static String CACHE_PARA_USER_DIC_GROUP_NAME = CACHE_PROJECT_ID + "userDicGroupName";
    /**
     * 登录人部门
     */
    public static String CACHE_PARA_USER_GROUP_NAME = CACHE_PROJECT_ID + "userGroupName";
    /**
     * 登录名
     */
    public static String CACHE_PARA_LOGIN_NAME = CACHE_PROJECT_ID + "loginName";


    //TP青岛更改 曹群星 2017-02-15 ADD START
    /**
     * 订单编号的流水号
     */
    public static String CACHE_PARA_ORDER_NUMBER_FLOWID = CACHE_PROJECT_ID + "orderNumberFlowId";
    //TP青岛更改 曹群星 2017-02-15 ADD END
}
