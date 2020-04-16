package com.johnyehyo.base.framework.config;

import com.johnyehyo.base.framework.security.RetryLimitHashedCredentialsMatcher;
import com.johnyehyo.base.framework.security.SysUserRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
public class ShiroConfig {


    private String url;
    private int timeout;
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

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(sysUserRealm());
        manager.setCacheManager(redisCacheManager());
        manager.setSessionManager(SessionManager());
        return manager;
    }
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
//        definition.addPathDefinition("/login", "anon");
        definition.addPathDefinition("/**", "authc");
        return definition;
    }
}
