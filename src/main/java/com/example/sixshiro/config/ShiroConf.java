package com.example.sixshiro.config;

import com.example.sixshiro.cache.ehcache.EhCacheCacheManager;
import com.example.sixshiro.scurtity.CustomRealm;
import com.example.sixshiro.scurtity.JWTFilter;
import com.example.sixshiro.scurtity.session.CacheSessionDAO;
import com.example.sixshiro.scurtity.session.SessionManager;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.*;

/**
 * @author: lh
 * @date: 2021/3/29
 */

@Configuration
public class ShiroConf {

    /**
     * 生命周期管理
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(name = "sessionManager")
    public SessionManager sessionManager(CacheSessionDAO dao) {
        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(dao);
        sessionManager.setGlobalSessionTimeout(86400000);
        sessionManager.setSessionValidationInterval(1800000);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookie(new SimpleCookie("wolfking.jeeplus.session.id"));
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @ConditionalOnProperty(name = "spring.cache.type", havingValue = "ehcache")
    @Bean
    public CacheManager shiroCacheManager(org.springframework.cache.ehcache.EhCacheCacheManager ehCacheCacheManager){
        EhCacheCacheManager shiroCacheManager = new EhCacheCacheManager();
        shiroCacheManager.setCacheManager(ehCacheCacheManager);
        return shiroCacheManager;
    }

//    @ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis")
//    @Bean
//    public CacheManager shiroRedisCacheManager(){
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        return redisCacheManager;
//    }
    //,
//
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(
            CustomRealm customRealm,
            CacheManager cacheManager,
            SessionManager sessionManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setCacheManager(cacheManager);
//        defaultWebSecurityManager.setRealm(systemAuthorizingRealm);
        Collection<Realm> typeRealms = new ArrayList<>();
        typeRealms.add(customRealm);
        defaultWebSecurityManager.setRealms(typeRealms);
        return defaultWebSecurityManager;
    }


    /**
     *  并发登录
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
////        filterMap.put("kickout", kickoutSessionControlFilter);
        factoryBean.setFilters(filterMap);
//
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl("/401");
//
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
//        // 访问401和404页面不通过我们的Filter anon 可匿名访问
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/login", "anon");
//        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }
}
