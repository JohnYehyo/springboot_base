package com.johnyehyo.base.system.domain;

import lombok.Data;

import java.util.List;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
@Data
public class ResourceEntity {

    private Integer id;
    private String name;
    /**
     * 资源类型
     */
    private String type;
    private String url;
    private int parent_id;
    private String parent_ids;
    private String permission;
    private String available;
    private String fn;
    private int sortno;
    private String icon;
    private List<ResourceEntity> children;

}
