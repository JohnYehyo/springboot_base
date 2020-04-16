package com.johnyehyo.base.system.mapper;

import com.johnyehyo.base.system.domain.ResourceEntity;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
public interface ISysResourceMapper {
    ResourceEntity getResource(Integer id);
}
