package com.johnyehyo.base.system.domain;

import lombok.Data;

import java.util.Date;

/**
 * 后台用户
 * @author JohnYehyo
 * @date 2020-4-16
 */
@Data
public class AdminEntity {

    /**
     * ID
     */
    private Integer id ;
    /**
     * 父部门ID
     */
    private String pid;
    private String t_push_id;
    /**
     * 账号
     */
    private String t_account;
    /**
     * 用户名
     */
    private String t_username;
    /**
     * 密码
     */
    private String t_password;
    /**
     * 性别
     */
    private String t_gender;
    /**
     * 年龄
     */
    private Integer t_age;
    /**
     * 角色
     */
    private Integer t_role;
    /**
     * 管理员
     */
    private String t_avator;
    /**
     * 联系方式
     */
    private String t_contact;
    /**
     * 单位名称
     */
    private String t_uint_name;
    /**
     * 登录次数
     */
    private Integer t_login_count;
    /**
     * 最后登录时间
     */
    private Date t_last_login;
    /**
     * 角色id
     */
    private String role_ids;
    /**
     * 角色名字
     */
    private String role_names;
    /**
     * 所属组织机构id
     */
    private Integer org_ids;
    /**
     * 所属组织机构名字
     */
    private String org_name;
    /**
     * 人员等级 专职:0 社会:1
     */
    private Integer t_user_level;
    /**
     * 人员名字
     */
    private String t_user_name;
    /**
     * 人员名字
     */
    private String t_organ_name;
    /**
     * APP版本
     */
    private String version;
}
