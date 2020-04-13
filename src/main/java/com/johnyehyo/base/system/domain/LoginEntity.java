package com.johnyehyo.base.system.domain;

import lombok.Data;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
@Data
public class LoginEntity {

    /**
     * 用户名
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";
}
