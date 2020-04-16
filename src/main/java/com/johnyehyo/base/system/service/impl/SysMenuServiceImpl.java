package com.johnyehyo.base.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.johnyehyo.base.common.utils.JWTUtil;
import com.johnyehyo.base.common.utils.MD5Util;
import com.johnyehyo.base.system.domain.BaseResEntity;
import com.johnyehyo.base.system.domain.LoginEntity;
import com.johnyehyo.base.system.domain.LoginUserEntity;
import com.johnyehyo.base.system.mapper.ISysMenuMapper;
import com.johnyehyo.base.system.service.ISysMenuService;
import com.johnyehyo.base.system.service.ISysRoleService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author JohnYehyo
 * @date 2020-4-13
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private ISysMenuMapper sysMenuMapper;
    @Autowired
    private ISysRoleService sysRoleService;


    @Override
    public String getMenuByRoles(String role_ids) {
        Set<String> permissions = sysRoleService.findPermissions(StringToInt(role_ids.split(",")));
        List<BaseResEntity> menus = findMenus(null, permissions);
        JSONArray jsonArray = new JSONArray();
        listToJsonArray(jsonArray, menus);
        return jsonArray.toString();
    }

    private void listToJsonArray(JSONArray jsonArray, List<BaseResEntity> resourceList) {
        for (BaseResEntity resource : resourceList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("text", resource.getName());

            if (resource.getFn() != null && !resource.getFn().equals("")) {
                jsonObject.put("type", resource.getFn());
            }
            if (!org.springframework.util.StringUtils.isEmpty(resource.getUrl())) {
                jsonObject.put("href", resource.getUrl());
                jsonObject.put("target", "iframepage");
            }

            List<BaseResEntity> child = resource.getChildren();
            if (child != null && child.size() > 0) {
                JSONArray childArray = new JSONArray();
                listToJsonArray(childArray, child);
                jsonObject.put("children", childArray);
            }

            jsonArray.add(jsonObject);
        }
    }

    private Integer[] StringToInt(String[] arrs) {
        Integer[] ints = new Integer[arrs.length];
        for (int i = 0; i < arrs.length; i++) {
            ints[i] = Integer.parseInt(arrs[i]);
        }
        return ints;
    }

    private List<BaseResEntity> findMenus(BaseResEntity baseResEntity, Set<String> permissions) {
        Integer parentId = 1;
        if (baseResEntity != null) {
            parentId = baseResEntity.getId();
        }
        List<BaseResEntity> resources = sysMenuMapper.getResources(parentId);
        List<BaseResEntity> menus = new ArrayList<BaseResEntity>();
        for (BaseResEntity resource : resources) {
            if (resource.isRootNode()) {
                continue;
            }
            if (!"menu".equals(resource.getType())) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            resource.setChildren(this.findMenus(resource, permissions));
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, BaseResEntity resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return false;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
