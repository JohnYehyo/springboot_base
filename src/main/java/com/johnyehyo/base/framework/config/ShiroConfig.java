package com.johnyehyo.base.framework.config;

import com.johnyehyo.base.framework.security.CORSAuthenticationFilter;
import com.johnyehyo.base.framework.security.RetryLimitHashedCredentialsMatcher;
import com.johnyehyo.base.framework.security.SysUserFilter;
import com.johnyehyo.base.framework.security.SysUserRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
@Configuration
public class ShiroConfig {

    @Value("${redis.url}")
    private String url;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.password}")
    private String password;

    @Bean
    RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(url);
        redisManager.setTimeout(timeout);
//        redisManager.setPassword(password);
        return redisManager;
    }

    @Bean
    RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("userid");
        return redisCacheManager;
    }

    @Bean
    RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    RetryLimitHashedCredentialsMatcher credentialsMatcher(){
        RetryLimitHashedCredentialsMatcher retry = new RetryLimitHashedCredentialsMatcher(redisCacheManager());
        retry.setHashAlgorithmName("md5");
        retry.setHashIterations(2);
        retry.setStoredCredentialsHexEncoded(false);
        return retry;
    }

    /**
     * 自定义realm
     * @return
     */
    @Bean
    SysUserRealm sysUserRealm() {
        SysUserRealm userRealm = new SysUserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }


    /**
     * sessionManager
     */
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(sysUserRealm());
        manager.setCacheManager(redisCacheManager());
        manager.setSessionManager(SessionManager());
        return manager;
    }

    public CORSAuthenticationFilter corsAuthenticationFilter(){
        return new CORSAuthenticationFilter();
    }

    /**
     * 过滤器工厂
     * @return
     */
    @Bean
    ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager());

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setSuccessUrl("/index");

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("sysUser", new SysUserFilter());
        filters.put("corsAuthenticationFilter", new CORSAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/unauthorized", "authc");
//        filterChainDefinitionMap.put("/sys/**", "sysUser");
        filterChainDefinitionMap.put("/sys/**", "corsAuthenticationFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注解支持
     * @return
     */
    @Bean
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
