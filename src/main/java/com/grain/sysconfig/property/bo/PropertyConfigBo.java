package com.grain.sysconfig.property.bo;

import com.grain.base.bo.BaseBo;

/**
 * Created by Administrator on 2015/5/11.
 */
public class PropertyConfigBo extends BaseBo {

    /**
     * add by jijun 20170303 客服产品淘掌柜客满审批
     */
    public static final String SYS_KEFU_PRODUCT_TZG_KEMANSHENPI_GROUP_ID = "process_kemanshenpi_kefu_group_id";

    /**
     * add by jijun 20170306 客服产品淘掌柜客满审批
     */
    public static final String SYS_KEFU_PRODUCT_TZG_KEMANSHENPI_TASK_EN_NAME = "process_taozhangguikemanshenpi_kefu_task_en_names";

    /**
     * 当前系统所使用的权限系统的英文名字
     * 用于缓存获取的标志（例如：数据字典，用户session等）
     */
    public static String SYS_HPMS_NAME = "hpms_name";
    /**
     * 流程配置 通配符 *号
     */
    public static String SYS_XING_HAO = "*";
    /**
     * 新单外单流程  服务分配审批 产品IDS
     * 多个用逗号分隔
     */
    public static final String SYS_FU_WU_FEN_PEI_PRODUCT_IDS = "process_fuwufenpei_product_ids";

    /**
     * 下单时  运营产品（EnCode） 锁定定金和服务费
     * 多个用逗号分隔
     */
    public static String YUNYING_PROENCODE_DINGJINFUWUFEI = "_proEnCode_dingjinfuwufei";
    /**
     * 新单外单流程  服务分配审批 公司IDS
     * 多个用逗号分隔
     */
    public static String SYS_FU_WU_FEN_PEI_COMPNAY_IDS = "process_fuwufenpei_company_ids";
    /**
     * 新单外单流程  服务分配审批 UED 服务IDS
     * 多个用逗号分隔
     */
    public static final String SYS_FU_WU_FEN_PEI_KEFU_GROUP_ID = "process_fuwufenpei_kefu_group_id";
    /**
     * 新单外单流程  服务分配审批 客服 服务IDS
     * 多个用逗号分隔
     */
    public static String SYS_FU_WU_FEN_PEI_UED_GROUP_ID = "process_fuwufenpei_ued_group_id";

    /**
     * 新单外单流程  服务分配审批 流程key
     * 多个用逗号分隔
     */
    public static final String SYS_FU_WU_FEN_PEI_PROCESS_KEY = "process_fuwufenpei_process_key";
    /**
     * 部门主管审批 的产品IDS
     * 多个用逗号分隔
     */
    public static String SYS_BU_MEN_ZHU_GUAN_SHEN_PI_PRODUCT_IDS = "process_bumenzhuguanshenpi_product_ids";
    /**
     * 部门主管审批 的节点的前一个节点英文名称S
     * 多个用逗号分隔
     */
    public static String SYS_BU_MEN_ZHU_GUAN_SHEN_PI_TASK_EN_NAMES = "process_bumenzhuguanshenpi_task_en_names";

    /**
     * 部门主管审批 的公司IDS
     * 多个用逗号分隔
     */
    public static String SYS_BU_MEN_ZHU_GUAN_SHEN_PI_COMPANYID_IDS = "process_bumenzhuguanshenpi_company_ids";
    /**
     * 部门主管审批 的流程keyS
     * 多个用逗号分隔
     */
    public static String SYS_BU_MEN_ZHU_GUAN_SHEN_PI_PROCESS_KEYS = "process_bumenzhuguanshenpi_process_keys";

    /**
     * 商务审批部门 的产品IDS
     * 多个用逗号分隔
     */
    public static String SYS_SHANG_WU_SHEN_PI_BU_MEN_PRODUCT_IDS = "process_shangwushenpibumen_product_ids";
    /**
     * 商务审批部门 的节点的前一个节点英文名称S
     * 多个用逗号分隔
     */
    public static String SYS_SHANG_WU_SHEN_PI_BU_MEN_TASK_EN_NAMES = "process_shangwushenpibumen_task_en_names";

    /**
     * 商务审批部门 的公司IDS
     * 多个用逗号分隔
     */
    public static String SYS_SHANG_WU_SHEN_PI_BU_MEN_COMPANYID_IDS = "process_shangwushenpibumen_company_ids";
    /**
     * 商务审批部门 的流程keyS
     * 多个用逗号分隔
     */
    public static String SYS_SHANG_WU_SHEN_PI_BU_MEN_PROCESS_KEYS = "process_shangwushenpibumen_process_keys";

    /**
     * 客服部门经理审批 的产品IDS
     * 多个用逗号分隔
     */
    public static String SYS_KEFU_BUMEN_JINGLI_SHENPI_PRODUCT_IDS = "process_kefubumenjinglishenpi_product_ids";
    /**
     * 客服部门经理审批 的节点的前一个节点英文名称S
     * 多个用逗号分隔
     */
    public static String SYS_KEFU_BUMEN_JINGLI_SHENPI_TASK_EN_NAMES = "process_kefubumenjinglishenpi_task_en_names";

    /**
     * 客服部门经理审批 的公司IDS
     * 多个用逗号分隔
     */
    public static String SYS_KEFU_BUMEN_JINGLI_SHENPI_COMPANYID_IDS = "process_kefubumenjinglishenpi_company_ids";
    /**
     * 客服部门经理审批 的流程keyS
     * 多个用逗号分隔
     */
    public static String SYS_KEFU_BUMEN_JINGLI_SHENPI_PROCESS_KEYS = "process_kefubumenjinglishenpi_process_keys";
    /**
     * 淘掌柜转单流程接单公司客满审批 的产品IDS
     * 多个用逗号分隔
     */
    public static String SYS_JIEDAN_KEMAN_SHENPI_PRODUCT_IDS = "process_jiedankemanshenpi_product_ids";
    /**
     * 淘掌柜转单流程接单公司客满审批 的节点的前一个节点英文名称S
     * 多个用逗号分隔
     */
    public static String SYS_JIEDAN_KEMAN_SHENPI_TASK_EN_NAMES = "process_jiedankemanshenpi_task_en_names";

    /**
     * 淘掌柜转单流程接单公司客满审批 的公司IDS
     * 多个用逗号分隔
     */
    public static String SYS_JIEDAN_KEMAN_SHENPI_COMPANYID_IDS = "process_jiedankemanshenpi_company_ids";
    /**
     * 淘掌柜转单流程接单公司客满审批 的流程keyS
     * 多个用逗号分隔
     */
    public static String SYS_JIEDAN_KEMAN_SHENPI_PROCESS_KEYS = "process_jiedankemanshenpi_process_keys";
    /**
     * 客服内单-须质检部审批 的产品IDS
     * 多个用逗号分隔
     */
    public static String SYS_XIADANNEI_ZHIJIANBU_SHENPI_PRODUCT_IDS = "process_zhijianbushenpi_product_ids";
    /**
     * 客服内单下单-须质检部审批 的节点的前一个节点英文名称S
     * 多个用逗号分隔
     */
    public static String SYS_XIADANNEI_ZHIJIANBU_SHENPI_TASK_EN_NAMES = "process_zhijianbushenpi_task_en_names";

    /**
     * 客服内单下单-须质检部审批 的公司IDS
     * 多个用逗号分隔
     */
    public static String SYS_XIADANNEI_ZHIJIANBU_SHENPI_COMPANYID_IDS = "process_zhijianbushenpi_company_ids";
    /**
     * 客服内单下单-须质检部审批 的流程keyS
     * 多个用逗号分隔
     */
    public static String SYS_XIADANNEI_ZHIJIANBU_SHENPI_PROCESS_KEYS = "process_zhijianbushenpi_process_keys";
    /**
     * 转单到淘掌柜须质检部、财务审批 的公司IDS
     * 多个用逗号分隔
     */
    public static final String SYS_ZHUANDAN_SHENPI_COMPANYID_IDS = "process_zhuandan_company_ids";
    /**
     * 转单到淘掌柜须质检部、财务审批 的流程keyS
     * 多个用逗号分隔
     */
    public static String SYS_ZHUANDAN_SHENPI_PROCESS_KEYS = "process_zhuandan_process_keys";
    /**
     * 转单到淘掌柜须质检部、财务审批 的流程product_idS
     * 多个用逗号分隔
     */
    public static String SYS_ZHUANDAN_SHENPI_PRODUCT_IDS = "process_zhuandan_product_ids";
    /**
     * 转单到淘掌柜须质检部、财务审批 的流程task_en_nameS
     * 多个用逗号分隔
     */
    public static String SYS_ZHUANDAN_SHENPI_TASK_EN_NAMES = "process_zhuandan_task_en_names";

    /**
     * 扣减业绩周期产品已投放的天数
     */
    public static String SYS_REDUCE_YITOUFANG_MAX_DAYS = "reduce_zhouqi_yitoufang_max_days";

    /**
     * 扣减业绩周期产品已投放的产品
     */
    public static String SYS_REDUCE_YITOUFANG_PRODUCT_IDS = "reduce_zhouqi_yitoufang_product_ids";

    /**
     * 最近结束订单提醒时间
     */
    public static final String RECENT_END_ORDER_DAYS = "recent_end_order_days";
    /**
     * 待办任务超时默认时间
     */
    public static final String DELAY_TODO_TASK_HOURS_DEFAULT = "delay_todo_task_hours_default";
    /**
     * 待办任务超时时间分组
     */
    public static final String DELAY_TODO_TASK_HOURS_GROUP = "delay_hours_process";

    /**
     * 不可以进行批量审批操作的节点分组
     */
    public static final String BAN_BATCH_APPROVAL = "ban_batch_approval";
    /**
     * UED打分须备注标准
     */
    public static final String UED_SCORE_MARK = "ued_score_mark";

    /**
     * vp关联的公司
     */
    public static final String SYS_VP_SHEN_PI_COMPANYID_IDS = "process_vpshenpi_company_ids";
    /**
     * vp审批流程key
     *
     * @return
     */
    public static final String SYS_VP_SHEN_PI_PROCESS_KEYS = "process_vpshenpi_process_keys";
    /**
     * vp审批的产品
     */
    public static final String SYS_VP_SHEN_PI_PRODUCT_IDS = "process_vpshenpi_product_ids";
    /**
     * vp审批任务名称
     *
     * @return
     */
    public static final String SYS_VP_SHEN_PI_TASK_EN_NAMES = "process_vpshenpi_task_en_names";

    /**
     * add by jijun 20170306 洪海公司id
     */
    public static final String SYS_HH_COMPANY_ID = "process_hh_company_id";

    /**
     * add by jijun 20170307 客服产品淘掌柜审批
     */
    public static final String KEFU_PRODUCT_TZG_KEMANSHENPI = "kefu_product_tzg_kemanshenpi";

    /**
     * 追加客服淘掌柜客满审批
     * add by jijun 20170316
     */
    public static final String SYS_PROCESS_ZHUIJIAKEFU_TZGKEMANSHENPI = "process_zhuijiakefu_tzgkemanshenpi";

    public static final String SYS_PROCESS_KEY_ZHUIJIAKEFU = "process_key_zhuijiakefu";

    /**
     * 定金process_key
     * add by jijun 20170316
     */
    public static final String SYS_PROCESS_KEY_DEPOSITSHENPI = "process_key_depositshenpi";
    /**
     * 可以下定金的产品
     * add by jijun 20170316
     */
    public static final String SYS_DEPOSITSHENPI_PRODUCT_IDS = "depositshenpi_product_ids";
    /**
     * 下定金申请
     * add by jijun 20170316
     */
    public static final String SYS_PROCESS_DEPOSITSHENPI_XIADINGJINSHENQING = "process_depositshenpi_xiadingjinshenqing";
    /**
     * 哪些流程节点可以提交给vp审批
     * add by jijun 20170418
     */
    public static final String PROCESS_WHICH_TASKENCODE_SUBMIT_TO_VPSHENPI = "process_which_taskencode_submit_to_vpshenpi";

    /**
     * 暂停流程需要走部门经理的产品
     * add by jijun 20170623
     *
     * @return
     */
    public static final String PAUSEORDERSHENPI_BMJL_APPROVE_PRODUCTIDS = "pauseordershenpi_bmjl_approve_productids";

    /**
     * 暂停审批中 洪海客满节点可以审批的产品productID
     * add by jijun 20170629
     */
    public static final String PAUSEORDERSHENPI_HHKM_APPROVE_PRODUCTIDS = "pauseordershenpi_hhkm_approve_productids";


    public String getProperty_group() {
        return property_group;
    }

    public void setProperty_group(String property_group) {
        this.property_group = property_group;
    }

    public String getProperty_value() {
        return property_value;
    }

    public void setProperty_value(String property_value) {
        this.property_value = property_value;
    }

    public String getProperty_key() {
        return property_key;
    }

    public void setProperty_key(String property_key) {
        this.property_key = property_key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String property_group;
    private String property_key;
    private String property_value;
    private String description;

}
