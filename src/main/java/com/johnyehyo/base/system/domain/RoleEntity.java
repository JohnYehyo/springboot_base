package com.johnyehyo.base.system.domain;

import lombok.Data;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
@Data
public class RoleEntity {
    private int id;

    private String role;

    private String description;

    private String resource_ids;

    private String resource_names;

    private String available;
}
