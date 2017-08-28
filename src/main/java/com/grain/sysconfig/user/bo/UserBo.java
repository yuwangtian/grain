package com.grain.sysconfig.user.bo;

import com.grain.base.bo.BaseBo;

import java.util.Date;

public class UserBo extends BaseBo {

    private Integer user_id;
    private String name;
    private Integer dic_group_id;//直接部门
    private String dic_group_name;//直接部门名称
    private Integer group_id;//大部门
    private String group_code;//部门编码
    private String group_name;//大部门名称
    private Integer company_id;//公司ID
    private String company_name;//公司名称
    private Integer parent_group_id;//父部门ID
    private String parent_group_name;//父部门名称
    private String login_name;
    private String password;
    private String agent_num;
    private Date birthday;
    private Integer sex;
    private Integer card_type;
    private String card_num;
    private Date entry_time;
    private Integer work_status; //工作状态 0试用期1正式2离职3停薪留职）
    private String cell_phone;
    private String email;
    private String qq;
    private String lord_flag;
    private String ids; //封装id的数组
    private String email1;//替换email
    private String age;//
    private String  shoujin_time;
    private String  shoujin_local_flag;
    private String  remark ;
    private String  shoujin_flag ;

    @Override
    public String toString() {
        return user_id+"";
    }

    public String getRole_en_name() {
        return role_en_name;
    }

    public void setRole_en_name(String role_en_name) {
        this.role_en_name = role_en_name;
    }

    private String role_en_name;//角色名称 （定金审批时 用于查找审批人员）

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public UserBo() {
        super();
    }

    public UserBo(String login_name) {
        super();
        this.login_name = login_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBo)) return false;

        UserBo userBo = (UserBo) o;

        return getUser_id().equals(userBo.getUser_id());

    }

    @Override
    public int hashCode() {
        return getUser_id().hashCode();
    }

    public String getShoujin_time() {
        return shoujin_time;
    }

    public void setShoujin_time(String shoujin_time) {
        this.shoujin_time = shoujin_time;
    }

    public String getShoujin_local_flag() {
        return shoujin_local_flag;
    }

    public void setShoujin_local_flag(String shoujin_local_flag) {
        this.shoujin_local_flag = shoujin_local_flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShoujin_flag() {
        return shoujin_flag;
    }

    public void setShoujin_flag(String shoujin_flag) {
        this.shoujin_flag = shoujin_flag;
    }

    public String getLord_flag() {
        return lord_flag;
    }

    public void setLord_flag(String lord_flag) {
        this.lord_flag = lord_flag;
    }

    public UserBo(Integer user_id) {
        super();
        this.user_id = user_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgent_num() {
        return agent_num;
    }

    public void setAgent_num(String agent_num) {
        this.agent_num = agent_num;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public Integer getWork_status() {
        return work_status;
    }

    public void setWork_status(Integer work_status) {
        this.work_status = work_status;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getDic_group_id() {
        return dic_group_id;
    }

    public void setDic_group_id(Integer dic_group_id) {
        this.dic_group_id = dic_group_id;
    }

    public String getDic_group_name() {
        return dic_group_name;
    }

    public void setDic_group_name(String dic_group_name) {
        this.dic_group_name = dic_group_name;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getParent_group_id() {
        return parent_group_id;
    }

    public void setParent_group_id(Integer parent_group_id) {
        this.parent_group_id = parent_group_id;
    }

    public String getParent_group_name() {
        return parent_group_name;
    }

    public void setParent_group_name(String parent_group_name) {
        this.parent_group_name = parent_group_name;
    }
}
