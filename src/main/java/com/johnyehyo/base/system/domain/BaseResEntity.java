package com.johnyehyo.base.system.domain;

import lombok.Data;

import java.util.List;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
@Data
public class BaseResEntity {

    private Integer id;
    private String name;
    private String type;
    private String url;
    private Integer parent_id;
    private String parent_ids;
    private String permission;
    private String available;
    private String fn;
    private Integer sortno;
    private List<BaseResEntity> children;

    public boolean isRootNode() {
        return parent_id == 0;
    }
}
