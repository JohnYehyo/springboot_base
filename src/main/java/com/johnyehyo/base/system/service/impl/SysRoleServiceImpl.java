package com.johnyehyo.base.system.service.impl;

import com.johnyehyo.base.system.domain.ResourceEntity;
import com.johnyehyo.base.system.domain.RoleEntity;
import com.johnyehyo.base.system.mapper.ISysResourceMapper;
import com.johnyehyo.base.system.mapper.ISysRoleMapper;
import com.johnyehyo.base.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private ISysRoleMapper sysRoleMapper;
    @Autowired
    private ISysResourceMapper sysResourceMapper;

    @Override
    public Set<String> findPermissions(Integer[] roleIds) {
        Set<Integer> resourceIds = new HashSet<Integer>();
        for (Integer roleId : roleIds) {
            RoleEntity role = sysRoleMapper.getRole(roleId);
            if (role != null) {
                String resource_ids = role.getResource_ids();
                String[] resourceids = null;
                if (resource_ids != null && resource_ids != "") {
                    resourceids = resource_ids.split(",");
                }
                List<Integer> ids = new ArrayList<Integer>();
                if (resourceids != null && resourceids.length > 0) {
                    for (int i = 0; i < resourceids.length; i++) {
                        ids.add(new Integer(resourceids[i]));
                    }
                }
                resourceIds.addAll(ids);
            }
        }
        return findPermissions(resourceIds);
    }

    public Set<String> findPermissions(Set<Integer> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for (Integer resourceId : resourceIds) {
            ResourceEntity resource = sysResourceMapper.getResource(resourceId);
            if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }
}
