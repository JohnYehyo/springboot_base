package com.johnyehyo.base.system.domain;

import lombok.Data;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
@Data
public class LoginUserEntity {

    /**
     * id
     *
     */
    private int id;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别
     */
    private int gender;

    /**
     * 机构id
     */
    private String pid;

    /**
     * 角色名
     */
    private String roleNames;

    /**
     * 角色id
     */
    private String roleIds;

    /**
     * 机构id
     */
    private String orgIds;

    /**
     * 机构名
     */
    private String orgName;
}
