package com.johnyehyo.base.system.mapper;


import com.johnyehyo.base.system.domain.BaseResEntity;

import java.util.List;
import java.util.Set;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
public interface ISysMenuMapper {

    List<BaseResEntity> findMenus(Set<String> permissions);

    List<BaseResEntity> getResources(Integer parentId);
}
